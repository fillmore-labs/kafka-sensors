package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData.EnumSymbol;
import org.apache.avro.generic.GenericEnumSymbol;

public final class SensorStateStateSchema {
  public static final String STATE_OFF = "OFF";
  public static final String STATE_ON = "ON";
  public static final GenericEnumSymbol<?> ENUM_OFF;
  public static final GenericEnumSymbol<?> ENUM_ON;
  public static final Schema SCHEMA;

  private static final String NAMESPACE = "com.fillmore_labs.kafka.sensors.avro";
  private static final String[] ENUM_ALIASES = {
    "com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SensorStateReflect.State",
    "com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateReflect.State"
  };

  static {
    SCHEMA =
        SchemaBuilder.enumeration("State")
            .namespace(NAMESPACE)
            .doc("New state of the sensor")
            .aliases(ENUM_ALIASES)
            .symbols(STATE_OFF, STATE_ON);

    ENUM_OFF = new EnumSymbol(SCHEMA, STATE_OFF);
    ENUM_ON = new EnumSymbol(SCHEMA, STATE_ON);
  }

  private SensorStateStateSchema() {}
}
