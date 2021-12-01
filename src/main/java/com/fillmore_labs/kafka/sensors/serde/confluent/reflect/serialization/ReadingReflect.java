package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import com.google.common.base.MoreObjects;
import java.time.Instant;
import java.util.Objects;
import org.apache.avro.reflect.AvroAlias;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.AvroEncode;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("Measurement")
public final class ReadingReflect {
  @AvroEncode(using = InstantAsLongEncoding.class)
  public Instant time;

  public Position position;

  @Override
  public int hashCode() {
    return Objects.hash(time, position);
  }

  @Override
  @SuppressWarnings("OperatorPrecedence")
  public boolean equals(@Nullable Object o) {
    return o == this
        || o instanceof ReadingReflect that
            && Objects.equals(time, that.time)
            && position == that.position;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("time", time).add("position", position).toString();
  }

  @AvroDoc("Position of a sensor")
  @AvroAlias(alias = "Position", space = "com.fillmore_labs.kafka.sensors.avro")
  @AvroAlias(
      alias = "Position",
      space = "com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.ReadingReflect")
  public enum Position {
    OFF,
    ON
  }
}
