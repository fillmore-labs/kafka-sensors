package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationNanosConversion;
import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.TimestampNanosConversion;
import com.google.common.base.MoreObjects;
import java.time.Duration;
import java.util.Objects;
import org.apache.avro.Schema;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.ReflectData;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("Duration a sensor was in this position")
public final class StateDurationReflect {

  public static final ReflectData MODEL;
  public static final Schema SCHEMA;

  static {
    MODEL = new ReflectData();
    MODEL.addLogicalTypeConversion(new TimestampNanosConversion());
    MODEL.addLogicalTypeConversion(new DurationNanosConversion());
    MODEL.setFastReaderEnabled(true);

    SCHEMA = MODEL.getSchema(StateDurationReflect.class);
  }

  public String id;
  public ReadingReflect reading;
  public Duration duration;

  @Override
  public int hashCode() {
    return Objects.hash(id, reading, duration);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    return o == this
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
