package com.fillmore_labs.kafka.sensors.serde.avro.schema_demo;

import static org.junit.Assert.*;

import org.junit.Test;

public class SchemaDemoTest {
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
