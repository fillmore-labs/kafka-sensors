package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationMicrosConversion;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.data.TimeConversions.TimestampMicrosConversion;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.StringType;

public final class SensorStateSchema {
  public static final String FIELD_ID = "id";
  public static final String FIELD_TIME = "time";
  public static final String FIELD_STATE = "state";
  public static final GenericData MODEL;
  public static final Schema SCHEMA;

  private static final String NAMESPACE = "com.fillmore_labs.kafka.sensors.avro";

  static {
    var timestampConversion = new TimestampMicrosConversion();
    var durationConversion = new DurationMicrosConversion();

    MODEL = new GenericData();
    MODEL.addLogicalTypeConversion(timestampConversion);
    MODEL.addLogicalTypeConversion(durationConversion);
    MODEL.setFastReaderEnabled(true);

    /* Reusable shortcut `.type(stringSchema)` for
     *   .type()
     *     .stringBuilder()
     *     .prop("avro.java.string", "String")
     *     .endString()
     */
    var stringSchema = Schema.create(Schema.Type.STRING);
    stringSchema.addProp(GenericData.STRING_PROP, StringType.String.name());

    var timestampSchema = timestampConversion.getRecommendedSchema();

    SCHEMA =
        SchemaBuilder.record("SensorState")
            .namespace(NAMESPACE)
            .doc("State change of a sensor")
            .fields()
            .name(FIELD_ID)
            .type(stringSchema)
            .noDefault()
            .name(FIELD_TIME)
            .type(timestampSchema)
            .noDefault()
            .name(FIELD_STATE)
            .type(SensorStateStateSchema.SCHEMA)
            .noDefault()
            .endRecord();
  }

  private SensorStateSchema() {}
}
