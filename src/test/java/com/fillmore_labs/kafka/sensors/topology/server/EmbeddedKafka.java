package com.fillmore_labs.kafka.sensors.topology.server;

import com.google.common.flogger.FluentLogger;
import java.nio.file.Path;
import kafka.server.KafkaConfig;
import kafka.server.KafkaRaftServer;
import kafka.server.Server;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.utils.Time;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import scala.Option;

public final class EmbeddedKafka {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private final int nodeId;
  private @MonotonicNonNull Path logDir;
  private @MonotonicNonNull Server kafka;
  private int brokerPort;

  public EmbeddedKafka(int nodeId) {
    this.nodeId = nodeId;
  }

  public void startup() {
    var clusterId = Uuid.randomUuid();

    logDir = EmbeddedKafkaHelper.createTempDirectory("kafka-log-");
    EmbeddedKafkaHelper.formatStorage(logDir, clusterId, nodeId);

    var ports = EmbeddedKafkaHelper.availableLocalPorts();
    var props = EmbeddedKafkaHelper.createProperties(logDir, ports, nodeId);
    var config = new KafkaConfig(props, /* doLog= */ false);

    kafka = new KafkaRaftServer(config, Time.SYSTEM, Option.empty());
    kafka.startup();

    brokerPort = ports.brokerPort();
  }

  public void shutdown() {
    assert logDir != null : "@AssumeAssertion(nullness): startup() not called";
    assert kafka != null : "@AssumeAssertion(nullness): startup() not called";

    kafka.shutdown();
    kafka.awaitShutdown();
    EmbeddedKafkaHelper.killRaftExpirationReaper();
    if (!EmbeddedKafkaHelper.recursiveDelete(logDir.toFile())) {
      logger.atWarning().log("Can't delete %s", logDir);
    }
  }

  public String getBrokerList() {
    return String.format("%s:%d", EmbeddedKafkaHelper.LOCALHOST_IP4, brokerPort);
  }
}
