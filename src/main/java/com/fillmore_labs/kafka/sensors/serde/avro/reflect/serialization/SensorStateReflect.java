package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.TimestampNanosConversion;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.apache.avro.Schema;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.ReflectData;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("State of a sensor")
public final class SensorStateReflect {
  public static final ReflectData MODEL;
  public static final Schema SCHEMA;

  static {
    MODEL = new ReflectData();
    MODEL.addLogicalTypeConversion(new TimestampNanosConversion());
    MODEL.setFastReaderEnabled(true);

    SCHEMA = MODEL.getSchema(SensorStateReflect.class);
  }

  public String id;
  public ReadingReflect reading;

  @Override
  public int hashCode() {
    return Objects.hash(id, reading);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    return o == this
        || (o instanceof SensorStateReflect that
            && Objects.equals(id, that.id)
            && Objects.equals(reading, that.reading));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("reading", reading).toString();
  }
}
