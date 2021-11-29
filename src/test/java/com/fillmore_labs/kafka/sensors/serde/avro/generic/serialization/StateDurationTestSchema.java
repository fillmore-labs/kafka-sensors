package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationNanosConversion;
import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.TimestampNanosConversion;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;

public final class StateDurationTestSchema {
  public static final String FIELD_ID = "id";
  public static final String FIELD_READING = "reading";
  public static final String FIELD_COMMENT = "comment";
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

    var durationSchema = durationConversion.getRecommendedSchema();

    SCHEMA =
        SchemaBuilder.record("StateDuration")
            .namespace(NAMESPACE)
            .doc("Duration a sensor was in this position")
            .fields()
            .requiredString(FIELD_ID)
            .name(FIELD_READING)
            .type(ReadingSchema.SCHEMA)
            .noDefault()
            .optionalString(FIELD_COMMENT)
            .name(FIELD_DURATION)
            .type(durationSchema)
            .noDefault()
            .endRecord();
  }

  private StateDurationTestSchema() {}
}
