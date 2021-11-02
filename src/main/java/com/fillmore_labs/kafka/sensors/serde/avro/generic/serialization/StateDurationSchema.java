package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationNanosConversion;
import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.TimestampNanosConversion;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.StringType;

public final class StateDurationSchema {
  public static final String FIELD_ID = "id";
  public static final String FIELD_READING = "reading";
  public static final String FIELD_DURATION = "duration";
  public static final Schema SCHEMA;
  public static final GenericData MODEL;

  private static final String NAMESPACE = "com.fillmore_labs.kafka.sensors.avro";

  static {
    var timestampConversion = new TimestampNanosConversion();
    var durationConversion = new DurationNanosConversion();

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

    var durationSchema = durationConversion.getRecommendedSchema();

    SCHEMA =
        SchemaBuilder.record("StateDuration")
            .namespace(NAMESPACE)
            .doc("Duration a sensor was in this position")
            .fields()
            .name(FIELD_ID)
            .type(stringSchema)
            .noDefault()
            .name(FIELD_READING)
            .type(ReadingSchema.SCHEMA)
            .noDefault()
            .name(FIELD_DURATION)
            .type(durationSchema)
            .noDefault()
            .endRecord();
  }

  private StateDurationSchema() {}
}
