package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.TimestampNanosConversion;
import java.time.Instant;
import java.util.Objects;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericContainer;
import org.apache.avro.reflect.AvroAlias;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.ReflectData;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@AvroDoc("Measurement")
public final class ReadingReflect implements GenericContainer {
  public static final ReflectData MODEL;
  public static final Schema SCHEMA;

  static {
    MODEL = new ReflectData();
    MODEL.addLogicalTypeConversion(new TimestampNanosConversion());
    MODEL.setFastReaderEnabled(true);

    SCHEMA = MODEL.getSchema(ReadingReflect.class);
  }

  public Instant time;
  public Position position;

  @Override
  public Schema getSchema() {
    return SCHEMA;
  }

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
    return MODEL.toString(this);
  }

  @AvroDoc("Position of a sensor")
  @AvroAlias(alias = "Position", space = "com.fillmore_labs.kafka.sensors.avro")
  @AvroAlias(
      alias = "Position",
      space =
          "com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.ReadingReflect")
  public enum Position {
    OFF,
    ON
  }
}
