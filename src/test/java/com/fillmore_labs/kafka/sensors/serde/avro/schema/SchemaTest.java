package com.fillmore_labs.kafka.sensors.serde.avro.schema;

import org.apache.avro.Schema;
import org.apache.avro.SchemaValidationException;
import org.apache.avro.SchemaValidator;
import org.apache.avro.SchemaValidatorBuilder;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class SchemaTest {
  private static @MonotonicNonNull SchemaValidator validator;

  private final Schema schema;
  private final Iterable<Schema> schemata;

  public SchemaTest(String description, Schema schema, Iterable<Schema> schemata) {
    this.schema = schema;
    this.schemata = schemata;
  }

  @BeforeClass
  @EnsuresNonNull("validator")
  public static void before() {
    validator = new SchemaValidatorBuilder().canReadStrategy().validateAll();
  }

  @Parameters(name = "{index}: {0}")
  public static Iterable<?> parameters() {
    return TestHelper.createParameters();
  }

  @Test
  @RequiresNonNull("validator")
  public void canReadAll() throws SchemaValidationException {
    validator.validate(schema, schemata);
  }
}
