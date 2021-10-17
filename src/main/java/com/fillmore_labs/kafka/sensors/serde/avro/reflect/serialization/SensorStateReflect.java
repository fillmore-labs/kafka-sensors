package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import com.google.common.base.MoreObjects;
import java.time.Instant;
import java.util.Objects;
import org.apache.avro.Schema;
import org.apache.avro.data.TimeConversions.TimestampMicrosConversion;
import org.apache.avro.reflect.AvroAlias;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.ReflectData;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("State change of a sensor")
public final class SensorStateReflect {
  public static final ReflectData MODEL;
  public static final Schema SCHEMA;

  static {
    MODEL = new ReflectData();
    MODEL.addLogicalTypeConversion(new TimestampMicrosConversion());
    MODEL.setFastReaderEnabled(true);

    SCHEMA = MODEL.getSchema(SensorStateReflect.class);
  }

  public String id;
  public Instant time;
  public State state;

  @Override
  public int hashCode() {
    return Objects.hash(id, time, state);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    return o == this
        || (o instanceof SensorStateReflect that
            && Objects.equals(id, that.id)
            && Objects.equals(time, that.time)
            && state == that.state);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("time", time)
        .add("state", state)
        .toString();
  }

  @AvroDoc("New state of the sensor")
  @AvroAlias(alias = "State", space = "com.fillmore_labs.kafka.sensors.avro")
  @AvroAlias(
      alias = "State",
      space =
          "com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateReflect")
  public enum State {
    OFF,
    ON
  }
}
