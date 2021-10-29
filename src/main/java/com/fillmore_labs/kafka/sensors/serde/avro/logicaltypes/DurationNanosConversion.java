package com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes;

import java.time.Duration;
import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public final class DurationNanosConversion extends Conversion<Duration> {
  private static final String DURATION_NANOS = "duration-nanos";
  private static final DurationNanos DURATION_NANOS_TYPE = new DurationNanos();

  public static DurationNanos durationNanos() {
    return DURATION_NANOS_TYPE;
  }

  @Override
  public Class<Duration> getConvertedType() {
    return Duration.class;
  }

  @Override
  public String getLogicalTypeName() {
    return DURATION_NANOS;
  }

  @Override
  public Schema getRecommendedSchema() {
    return durationNanos().addToSchema(Schema.create(Schema.Type.LONG));
  }

  @Override
  public Duration fromLong(Long nanos, Schema schema, LogicalType type) {
    return Duration.ofNanos(nanos);
  }

  @Override
  public Long toLong(Duration duration, Schema schema, LogicalType type) {
    return duration.toNanos();
  }

  public static final class DurationNanos extends LogicalType {
    private DurationNanos() {
      super(DURATION_NANOS);
    }

    @Override
    public void validate(Schema schema) {
      super.validate(schema);
      if (schema.getType() != Schema.Type.LONG) {
        throw new IllegalArgumentException(
            "Duration (nanos) can only be used with an underlying long type");
      }
    }
  }
}
