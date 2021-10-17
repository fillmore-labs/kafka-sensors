package com.fillmore_labs.kafka.sensors.topology.server;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.flogger.FluentLogger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serial;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Predicate;
import kafka.tools.StorageTool;
import org.apache.kafka.common.utils.Exit;

/* package */ final class EmbeddedKafkaHelper {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private EmbeddedKafkaHelper() {}

  /* package */ static void killRaftExpirationReaper() {
    var isRaftExpirationReaper =
        (Predicate<Thread>) thread -> thread.getName().equals("raft-expiration-reaper");

    Thread.getAllStackTraces().keySet().stream()
        .filter(isRaftExpirationReaper)
        .forEach(Thread::interrupt);
  }

  /* package */ static void formatStorage(Properties props, UUID clusterId) {
    var propFile = writeProperties(props);

    callStorageTool(
        "format", "--config", propFile.toString(), "--cluster-id", uuid2Base64(clusterId));

    if (!propFile.delete()) {
      logger.atWarning().log("Failed to delete properties file");
    }
  }

  private static File writeProperties(Properties props) {
    File propFile;
    try {
      propFile = File.createTempFile("kafka-", ".properties");
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    try (var writer = new FileWriter(propFile, UTF_8)) {
      props.store(writer, null);
    } catch (IOException e) {
      propFile.deleteOnExit();
      throw new UncheckedIOException(e);
    }

    return propFile;
  }

  private static String uuid2Base64(UUID uuid) {
    var uuidBytes = ByteBuffer.allocate(2 * Long.BYTES);
    uuidBytes.putLong(uuid.getMostSignificantBits());
    uuidBytes.putLong(uuid.getLeastSignificantBits());

    var encoder = Base64.getUrlEncoder().withoutPadding();
    return encoder.encodeToString(uuidBytes.array());
  }

  private static void callStorageTool(String... args) {
    try {
      Exit.setExitProcedure(
          (statusCode, message) -> {
            throw new NoErrorException();
          });
      StorageTool.main(args);
    } catch (NoErrorException e) {
      // ignore
    } finally {
      Exit.resetExitProcedure();
    }
  }

  private static final class NoErrorException extends RuntimeException {
    @Serial private static final long serialVersionUID = -1L;
  }
}
