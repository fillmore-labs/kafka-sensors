package com.fillmore_labs.kafka.sensors.serde.avro.schema_demo;

public final class Main {
  private Main() {}

  public static void main(String... args) {
    var schemaDemo = new SchemaDemo();

    schemaDemo.validateSchemata();

    schemaDemo.tryRawEncoding();

    schemaDemo.tryBinaryEncoding();
  }
}
