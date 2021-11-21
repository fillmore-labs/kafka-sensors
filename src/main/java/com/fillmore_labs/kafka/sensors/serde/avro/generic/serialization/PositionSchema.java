package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;

public final class PositionSchema {
  public static final String POSITION_OFF = "OFF";
  public static final String POSITION_ON = "ON";
  public static final GenericData MODEL;
  public static final Schema SCHEMA;
  public static final Object ENUM_OFF;
  public static final Object ENUM_ON;

  private static final String NAMESPACE = "com.fillmore_labs.kafka.sensors.avro";
  private static final String[] ENUM_ALIASES = {
    "com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.ReadingReflect.Position",
    "com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.ReadingReflect.Position"
  };

  static {
    MODEL = new GenericData();

    SCHEMA =
        SchemaBuilder.enumeration("Position")
            .namespace(NAMESPACE)
            .doc("Position of a sensor")
            .aliases(ENUM_ALIASES)
            .symbols(POSITION_OFF, POSITION_ON);

    ENUM_OFF = MODEL.createEnum(POSITION_OFF, SCHEMA);
    ENUM_ON = MODEL.createEnum(POSITION_ON, SCHEMA);
  }

  private PositionSchema() {}
}
