package com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.PositionSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.ReadingSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.StateDurationSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.StateDurationTestSchema;
import java.time.Duration;
import java.time.Instant;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;

public final class TestHelper {
  /* package */ static final long INSTANT =
      Instant.ofEpochSecond(443_634_300L).toEpochMilli() * 1_000_000L + 1L;

  private TestHelper() {}

  /* package */ static GenericRecord createTestStateDuration() {
    var reading = createReading();

    return new GenericRecordBuilder(StateDurationTestSchema.SCHEMA)
        .set(StateDurationTestSchema.FIELD_ID, "7331")
        .set(StateDurationTestSchema.FIELD_READING, reading)
        .set(StateDurationTestSchema.FIELD_COMMENT, "New Field")
        .set(StateDurationTestSchema.FIELD_DURATION, Duration.ofSeconds(15).toNanos())
        .build();
  }

  /* package */ static GenericRecord createStateDuration() {
    var reading = createReading();

    return new GenericRecordBuilder(StateDurationSchema.SCHEMA)
        .set(StateDurationSchema.FIELD_ID, "7331")
        .set(StateDurationSchema.FIELD_READING, reading)
        .set(StateDurationSchema.FIELD_DURATION, Duration.ofSeconds(15).toNanos())
        .build();
  }

  private static GenericRecord createReading() {
    return new GenericRecordBuilder(ReadingSchema.SCHEMA)
        .set(ReadingSchema.FIELD_TIME, INSTANT)
        .set(ReadingSchema.FIELD_POSITION, PositionSchema.ENUM_OFF)
        .build();
  }
}
