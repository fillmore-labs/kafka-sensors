package com.fillmore_labs.kafka.sensors.serde.avro.schema_demo;

import com.google.common.flogger.FluentLogger;

public final class Main {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private Main() {}

  public static void main(String... args) {
    var schemaDemo = new SchemaDemo();

    schemaDemo.validateSchemata();

    logger.atInfo().log("***\n");

    schemaDemo.tryRawEncoding();

    logger.atInfo().log("***\n");

    schemaDemo.logFingerprints();

    logger.atInfo().log("***\n");

    schemaDemo.tryBinaryEncoding();
  }
}
