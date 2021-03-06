package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.TimestampNanosConversion;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;

public final class SensorStateSchema {
  public static final String FIELD_ID = "id";
  public static final String FIELD_READING = "reading";
  public static final GenericData MODEL;
  public static final Schema SCHEMA;

  private static final String NAMESPACE = "com.fillmore_labs.kafka.sensors.avro";

  static {
    var timestampConversion = new TimestampNanosConversion();

    MODEL = new GenericData();
    MODEL.addLogicalTypeConversion(timestampConversion);
    MODEL.setFastReaderEnabled(true);

    SCHEMA =
        SchemaBuilder.record("SensorState")
            .namespace(NAMESPACE)
            .doc("State of a sensor")
            .fields()
            .requiredString(FIELD_ID)
            .name(FIELD_READING)
            .type(ReadingSchema.SCHEMA)
            .noDefault()
            .endRecord();
  }

  private SensorStateSchema() {}
}
