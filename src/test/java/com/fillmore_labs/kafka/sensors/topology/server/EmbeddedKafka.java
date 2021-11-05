package com.fillmore_labs.kafka.sensors.topology.server;

import com.google.common.flogger.FluentLogger;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Properties;
import kafka.server.KafkaConfig;
import kafka.server.KafkaRaftServer;
import kafka.server.Server;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.utils.Time;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.junit.rules.ExternalResource;
import scala.Option;

public final class EmbeddedKafka extends ExternalResource {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private static final int NODE_ID = 1;
  private static final String BROKER = "BROKER";
  private static final String CONTROLLER = "CONTROLLER";

  private @MonotonicNonNull File logDir;
  private @MonotonicNonNull Server kafka;
  private int brokerPort;

  public EmbeddedKafka() {
    super();
  }

  private static Ports availableLocalPorts() {
    var loopback = Inet4Address.getLoopbackAddress();
    var randomLocalPort = new InetSocketAddress(loopback, 0);

    int port1;
    int port2;
    try (var ss1 = new ServerSocket();
        var ss2 = new ServerSocket()) {
      ss1.setReuseAddress(true);
      ss2.setReuseAddress(true);

      ss1.bind(randomLocalPort);
      ss2.bind(randomLocalPort);

      port1 = ss1.getLocalPort();
      port2 = ss2.getLocalPort();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    return new Ports(port1, port2);
  }

  private static Properties createProperties(File logDir, Ports ports) {
    var props = new Properties();

    props.put(KafkaConfig.ProcessRolesProp(), "broker,controller");
    props.put(KafkaConfig.NodeIdProp(), Integer.toString(NODE_ID));

    props.put(
        KafkaConfig.QuorumVotersProp(),
        String.format("%d@127.0.0.1:%d", NODE_ID, ports.controllerPort));

    props.put(
        KafkaConfig.ListenersProp(),
        String.format(
            "%s://127.0.0.1:%d,%s://127.0.0.1:%d",
            BROKER, ports.brokerPort, CONTROLLER, ports.controllerPort));
    props.put(KafkaConfig.InterBrokerListenerNameProp(), BROKER);
    props.put(KafkaConfig.ControllerListenerNamesProp(), CONTROLLER);

    props.put(
        KafkaConfig.AdvertisedListenersProp(),
        String.format("%s://127.0.0.1:%d", BROKER, ports.brokerPort));
    props.put(
        KafkaConfig.ListenerSecurityProtocolMapProp(),
        String.format("%s:PLAINTEXT,%s:PLAINTEXT", BROKER, CONTROLLER));

    props.put(KafkaConfig.LogDirProp(), logDir.toString());

    props.put(KafkaConfig.AutoCreateTopicsEnableProp(), "false");

    return props;
  }

  @Override
  @EnsuresNonNull({"logDir", "kafka"})
  protected void before() {
    try {
      logDir = Files.createTempDirectory("kafka-log-").toFile();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    var ports = availableLocalPorts();

    var props = createProperties(logDir, ports);

    var clusterId = Uuid.randomUuid();
    // EmbeddedKafkaHelper.formatStorage(props, clusterId);
    EmbeddedKafkaHelper.formatStorage(logDir, clusterId, NODE_ID);

    var config = new KafkaConfig(props, /* doLog= */ false);
    kafka = new KafkaRaftServer(config, Time.SYSTEM, Option.empty());
    kafka.startup();

    brokerPort = ports.brokerPort;
  }

  @Override
  protected void after() {
    assert logDir != null : "@AssumeAssertion(nullness): before() not called";
    assert kafka != null : "@AssumeAssertion(nullness): before() not called";

    kafka.shutdown();
    kafka.awaitShutdown();
    EmbeddedKafkaHelper.killRaftExpirationReaper();
    if (!recursiveDelete(logDir)) {
      logger.atWarning().log("Can't delete %s", logDir);
    }
  }

  private boolean recursiveDelete(File file) {
    if (file.delete()) {
      return true;
    }
    var files = file.listFiles();
    if (files != null && !Arrays.stream(files).allMatch(this::recursiveDelete)) {
      return false;
    }
    return file.delete();
  }

  public String getBrokerList() {
    return String.format("localhost:%d", brokerPort);
  }

  @SuppressWarnings("UnusedVariable")
  private record Ports(int brokerPort, int controllerPort) {}
}
