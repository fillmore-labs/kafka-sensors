package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.apache.avro.reflect.AvroDoc;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("State of a sensor")
public final class SensorStateReflect {
  public String id;

  public EventReflect event;

  @Override
  public int hashCode() {
    return Objects.hash(id, event);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    return this == o
        || (o instanceof SensorStateReflect that
            && Objects.equals(id, that.id)
            && Objects.equals(event, that.event));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("event", event).toString();
  }
}
