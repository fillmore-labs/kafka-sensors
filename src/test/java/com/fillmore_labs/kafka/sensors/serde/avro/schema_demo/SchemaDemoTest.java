package com.fillmore_labs.kafka.sensors.serde.avro.schema_demo;

import org.junit.Test;

public final class SchemaDemoTest {
  private final SchemaDemo schemaDemo = new SchemaDemo();

  @Test
  public void validateSchemata() {
    schemaDemo.validateSchemata();
  }

  @Test
  public void tryRawEncoding() {
    schemaDemo.tryRawEncoding();
  }

  @Test
  public void tryBinaryEncoding() {
    schemaDemo.tryBinaryEncoding();
  }
}
