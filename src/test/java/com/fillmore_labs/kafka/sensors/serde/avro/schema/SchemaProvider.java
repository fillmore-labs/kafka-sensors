package com.fillmore_labs.kafka.sensors.serde.avro.schema;

import com.fillmore_labs.kafka.sensors.avro.Position;
import com.fillmore_labs.kafka.sensors.avro.Reading;
import com.fillmore_labs.kafka.sensors.avro.SensorState;
import com.fillmore_labs.kafka.sensors.avro.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.PositionSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.ReadingSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.StateDurationSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.ReadingReflect;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SensorStateReflect;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.StateDurationReflect;
import com.google.testing.junit.testparameterinjector.TestParameters.TestParametersValues;
import com.google.testing.junit.testparameterinjector.TestParameters.TestParametersValuesProvider;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.apache.avro.Schema;

/* package */ final class SchemaProvider implements TestParametersValuesProvider {
  private static Stream<TestParametersValues> combinations(Collection<Schema> schemata) {
    return schemata.stream()
        .map(
            schema ->
                TestParametersValues.builder()
                    .name(schema.getFullName())
                    .addParameter("schema", schema)
                    .addParameter("schemata", schemata)
                    .build());
  }

  @Override
  public List<TestParametersValues> provideValues() {
    var positionSchemas =
        List.of(
            Position.getClassSchema(),
            PositionSchema.SCHEMA,
            ReadingReflect.MODEL.getSchema(ReadingReflect.Position.class));

    var readingSchemas =
        List.of(Reading.getClassSchema(), ReadingSchema.SCHEMA, ReadingReflect.SCHEMA);

    var sensorStateSchemas =
        List.of(SensorState.getClassSchema(), SensorStateSchema.SCHEMA, SensorStateReflect.SCHEMA);

    var stateDurationSchemas =
        List.of(
            StateDuration.getClassSchema(),
            StateDurationSchema.SCHEMA,
            StateDurationReflect.SCHEMA);

    return Stream.of(positionSchemas, readingSchemas, sensorStateSchemas, stateDurationSchemas)
        .flatMap(SchemaProvider::combinations)
        .toList();
  }
}
