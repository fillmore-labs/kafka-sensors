package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationMicrosConversion;
import com.google.common.base.MoreObjects;
import java.time.Duration;
import java.util.Objects;
import org.apache.avro.Schema;
import org.apache.avro.data.TimeConversions.TimestampMicrosConversion;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.ReflectData;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("Duration a sensor was in this state")
public final class SensorStateDurationReflect {

  public static final ReflectData MODEL;
  public static final Schema SCHEMA;

  static {
    MODEL = new ReflectData();
    MODEL.addLogicalTypeConversion(new TimestampMicrosConversion());
    MODEL.addLogicalTypeConversion(new DurationMicrosConversion());
    MODEL.setFastReaderEnabled(true);

    SCHEMA = MODEL.getSchema(SensorStateDurationReflect.class);
  }

  public SensorStateReflect event;
  public Duration duration;

  @Override
  public int hashCode() {
    return Objects.hash(event, duration);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    return o == this
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
