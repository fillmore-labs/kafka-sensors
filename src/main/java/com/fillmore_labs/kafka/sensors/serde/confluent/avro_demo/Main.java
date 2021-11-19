package com.fillmore_labs.kafka.sensors.serde.confluent.avro_demo;

import com.google.common.flogger.FluentLogger;

public final class Main {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private Main() {}

  public static void main(String... args) {
    var decodingSample = new DecodingSample();

    decodingSample.tryGeneric();

    decodingSample.validateAvroCompatibility();

    decodingSample.trySpecific();

    decodingSample.validateConfluentCompatibility();

    try {
      decodingSample.trySpecificConfluent();
    } catch (ClassCastException e) {
      logger.atSevere().withCause(e).log("Can not deserializ with Confluent specific");
    }

    logger.atInfo().log("Done.");
  }
}
