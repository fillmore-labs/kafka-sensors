package com.fillmore_labs.kafka.sensors.topology.server;

import static java.nio.charset.StandardCharsets.UTF_8;
import static scala.jdk.javaapi.CollectionConverters.asScala;

import com.google.common.flogger.FluentLogger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import kafka.server.KafkaConfig;
import kafka.server.MetaProperties;
import kafka.tools.StorageTool;
import org.apache.kafka.common.Uuid;
import scala.collection.immutable.Seq;

/* package */ final class EmbeddedKafkaHelper {
  /* package */ static final String LOCALHOST_IP4 = "127.0.0.1";

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private static final String BROKER = "BROKER";
  private static final String CONTROLLER = "CONTROLLER";

  private EmbeddedKafkaHelper() {}

  /* package */ static Path createTempDirectory(String prefix) {
    try {
      return Files.createTempDirectory(prefix);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  /* package */ static void formatStorage(Path logDir, Uuid clusterId, int nodeId) {
    var logDirs = Seq.from(asScala(List.of(logDir.toString())));

    var properties = new MetaProperties(clusterId.toString(), nodeId);
    var out = new ByteArrayOutputStream();
    int result;
    try (var stream = new PrintStream(out, /* autoFlush= */ false, UTF_8)) {
      result = StorageTool.formatCommand(stream, logDirs, properties, /* ignoreFormatted= */ true);
    }
    if (result == 0) {
      logger.atInfo().log("Successful formatted Kafka log dir: %s", out.toString(UTF_8));
    } else {
      logger.atWarning().log("Formatting returned error %d: %s", result, out.toString(UTF_8));
    }
  }

  /* package */ static void killRaftExpirationReaper() {
    var isRaftExpirationReaper =
        (Predicate<Thread>) thread -> thread.getName().equals("raft-expiration-reaper");

    Thread.getAllStackTraces().keySet().stream()
        .filter(isRaftExpirationReaper)
        .forEach(Thread::interrupt);
  }

  /* package */ static Ports availableLocalPorts() {
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

  /* package */ static boolean recursiveDelete(File file) {
    if (file.delete()) {
      return true;
    }
    var files = file.listFiles();
    if (files != null && !Arrays.stream(files).allMatch(EmbeddedKafkaHelper::recursiveDelete)) {
      return false;
    }
    return file.delete();
  }

  /* package */ static Properties createProperties(Path logDir, Ports ports, int nodeId) {
    var quorumVoters = String.format("%d@%s:%d", nodeId, LOCALHOST_IP4, ports.controllerPort());

    var broker = String.format("%s://%s:%d", BROKER, LOCALHOST_IP4, ports.brokerPort());
    var controller = String.format("%s://%s:%d", CONTROLLER, LOCALHOST_IP4, ports.controllerPort());
    var listeners = String.join(",", broker, controller);

    var props = new Properties();
    props.put(KafkaConfig.ProcessRolesProp(), "broker,controller");
    props.put(KafkaConfig.NodeIdProp(), Integer.toString(nodeId));
    props.put(KafkaConfig.QuorumVotersProp(), quorumVoters);
    props.put(KafkaConfig.ListenersProp(), listeners);
    props.put(KafkaConfig.InterBrokerListenerNameProp(), BROKER);
    props.put(KafkaConfig.ControllerListenerNamesProp(), CONTROLLER);

    props.put(KafkaConfig.AdvertisedListenersProp(), broker);
    props.put(
        KafkaConfig.ListenerSecurityProtocolMapProp(),
        String.format("%s:PLAINTEXT,%s:PLAINTEXT", BROKER, CONTROLLER));

    props.put(KafkaConfig.LogDirProp(), logDir.toString());

    props.put(KafkaConfig.AutoCreateTopicsEnableProp(), "false");

    return props;
  }

  @SuppressWarnings("UnusedVariable")
  /* package */ record Ports(int brokerPort, int controllerPort) {}
}
