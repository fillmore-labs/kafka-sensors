package com.fillmore_labs.kafka.sensors.serde.avro.schema;

import com.google.testing.junit.testparameterinjector.TestParameterInjector;
import com.google.testing.junit.testparameterinjector.TestParameters;
import org.apache.avro.Schema;
import org.apache.avro.SchemaValidationException;
import org.apache.avro.SchemaValidator;
import org.apache.avro.SchemaValidatorBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(TestParameterInjector.class)
public final class SchemaTest {
  private static final SchemaValidator VALIDATOR =
      new SchemaValidatorBuilder().canReadStrategy().validateAll();

  @Test
  @TestParameters(valuesProvider = SchemaProvider.class)
  public void canReadAll(Schema schema, Iterable<Schema> schemata)
      throws SchemaValidationException {
    VALIDATOR.validate(schema, schemata);
  }
}
