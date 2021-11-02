package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import com.google.common.base.MoreObjects;
import java.time.Duration;
import java.util.Objects;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.AvroEncode;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("Duration a sensor was in this position")
public final class StateDurationReflect {
  public String id;

  public ReadingReflect reading;

  @AvroEncode(using = DurationAsLongEncoding.class)
  public Duration duration;

  @Override
  public int hashCode() {
    return Objects.hash(id, reading, duration);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    return this == o
        || (o instanceof StateDurationReflect that
            && Objects.equals(id, that.id)
            && Objects.equals(reading, that.reading)
            && Objects.equals(duration, that.duration));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("reading", reading)
        .add("duration", duration)
        .toString();
  }
}
