package com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes;

import static org.junit.Assert.assertThrows;

import org.apache.avro.Schema;
import org.junit.Test;

public final class SchemaTest {
  @Test
  public void durationNanosSchema() {
    var nullSchema = Schema.create(Schema.Type.NULL);
    assertThrows(
        IllegalArgumentException.class,
        () -> DurationNanosConversion.durationNanos().addToSchema(nullSchema));
  }

  @Test
  public void timestampNanosSchema() {
    var nullSchema = Schema.create(Schema.Type.NULL);
    assertThrows(
        IllegalArgumentException.class,
        () -> TimestampNanosConversion.timestampNanos().addToSchema(nullSchema));
  }
}
