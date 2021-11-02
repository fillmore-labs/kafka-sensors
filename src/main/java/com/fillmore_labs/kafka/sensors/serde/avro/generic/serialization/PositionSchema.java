package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData.EnumSymbol;
import org.apache.avro.generic.GenericEnumSymbol;

public final class PositionSchema {
  public static final String POSITION_OFF = "OFF";
  public static final String POSITION_ON = "ON";
  public static final GenericEnumSymbol<?> ENUM_OFF;
  public static final GenericEnumSymbol<?> ENUM_ON;
  public static final Schema SCHEMA;

  private static final String NAMESPACE = "com.fillmore_labs.kafka.sensors.avro";
  private static final String[] ENUM_ALIASES = {
    "com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.ReadingReflect.Position",
    "com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.ReadingReflect.Position"
  };

  static {
    SCHEMA =
        SchemaBuilder.enumeration("Position")
            .namespace(NAMESPACE)
            .doc("Position of a sensor")
            .aliases(ENUM_ALIASES)
            .symbols(POSITION_OFF, POSITION_ON);

    ENUM_OFF = new EnumSymbol(SCHEMA, POSITION_OFF);
    ENUM_ON = new EnumSymbol(SCHEMA, POSITION_ON);
  }

  private PositionSchema() {}
}
