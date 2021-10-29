package com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes;

import java.time.Instant;
import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public final class TimestampNanosConversion extends Conversion<Instant> {
  private static final String TIMESTAMP_NANOS = "timestamp-nanos";
  private static final TimestampNanos TIMESTAMP_NANOS_TYPE = new TimestampNanos();

  public static TimestampNanos timestampNanos() {
    return TIMESTAMP_NANOS_TYPE;
  }

  @Override
  public Class<Instant> getConvertedType() {
    return Instant.class;
  }

  @Override
  public String getLogicalTypeName() {
    return TIMESTAMP_NANOS;
  }

  @Override
  public Schema getRecommendedSchema() {
    return timestampNanos().addToSchema(Schema.create(Schema.Type.LONG));
  }

  @Override
  public Instant fromLong(Long nanos, Schema schema, LogicalType type) {
    return InstantNanosHelper.nanos2Instant(nanos);
  }

  @Override
  public Long toLong(Instant timestamp, Schema schema, LogicalType type) {
    return InstantNanosHelper.instant2Nanos(timestamp);
  }

  public static final class TimestampNanos extends LogicalType {
    private TimestampNanos() {
      super(TIMESTAMP_NANOS);
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
