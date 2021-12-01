package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.TimestampNanosConversion;
import java.util.Objects;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericContainer;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.ReflectData;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("State of a sensor")
public final class SensorStateReflect implements GenericContainer {
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
  public Schema getSchema() {
    return SCHEMA;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, reading);
  }

  @Override
  @SuppressWarnings("OperatorPrecedence")
  public boolean equals(@Nullable Object o) {
    return o == this
        || o instanceof SensorStateReflect that
            && Objects.equals(id, that.id)
            && Objects.equals(reading, that.reading);
  }

  @Override
  public String toString() {
    return MODEL.toString(this);
  }
}
