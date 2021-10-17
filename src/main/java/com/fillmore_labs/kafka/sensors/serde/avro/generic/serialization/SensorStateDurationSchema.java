package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationMicrosConversion;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.data.TimeConversions.TimestampMicrosConversion;
import org.apache.avro.generic.GenericData;

public final class SensorStateDurationSchema {
  public static final String FIELD_EVENT = "event";
  public static final String FIELD_DURATION = "duration";
  public static final Schema SCHEMA;
  public static final GenericData MODEL;

  private static final String NAMESPACE = "com.fillmore_labs.kafka.sensors.avro";

  static {
    var timestampConversion = new TimestampMicrosConversion();
    var durationConversion = new DurationMicrosConversion();

    MODEL = new GenericData();
    MODEL.addLogicalTypeConversion(timestampConversion);
    MODEL.addLogicalTypeConversion(durationConversion);
    MODEL.setFastReaderEnabled(true);

    var durationSchema = durationConversion.getRecommendedSchema();

    SCHEMA =
        SchemaBuilder.record("SensorStateDuration")
            .namespace(NAMESPACE)
            .doc("Duration a sensor was in this state")
            .fields()
            .name(FIELD_EVENT)
            .type(SensorStateSchema.SCHEMA)
            .noDefault()
            .name(FIELD_DURATION)
            .type(durationSchema)
            .noDefault()
            .endRecord();
  }

  private SensorStateDurationSchema() {}
}
