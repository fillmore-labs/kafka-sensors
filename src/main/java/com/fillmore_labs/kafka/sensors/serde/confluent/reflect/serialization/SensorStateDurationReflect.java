package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import com.google.common.base.MoreObjects;
import java.time.Duration;
import java.util.Objects;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.AvroEncode;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("Duration a sensor was in this state")
public final class SensorStateDurationReflect {
  public SensorStateReflect event;

  @AvroEncode(using = DurationAsLongEncoding.class)
  public Duration duration;

  @Override
  public int hashCode() {
    return Objects.hash(event, duration);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    return this == o
        || (o instanceof SensorStateDurationReflect that
            && Objects.equals(event, that.event)
            && Objects.equals(duration, that.duration));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("event", event)
        .add("duration", duration)
        .toString();
  }
}
