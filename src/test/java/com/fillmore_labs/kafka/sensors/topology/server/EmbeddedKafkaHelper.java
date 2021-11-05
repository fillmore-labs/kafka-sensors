package com.fillmore_labs.kafka.sensors.topology.server;

import static java.nio.charset.StandardCharsets.UTF_8;
import static scala.jdk.javaapi.CollectionConverters.asScala;

import com.google.common.flogger.FluentLogger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Predicate;
import kafka.server.MetaProperties;
import kafka.tools.StorageTool;
import org.apache.kafka.common.Uuid;
import scala.collection.immutable.Seq;

/* package */ final class EmbeddedKafkaHelper {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private EmbeddedKafkaHelper() {}

  /* package */ static void formatStorage(File logDir, Uuid clusterId, int nodeId) {
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
}
