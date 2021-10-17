package com.fillmore_labs.kafka.sensors.serde.avro.schema;

import com.fillmore_labs.kafka.sensors.avro.SensorState;
import com.fillmore_labs.kafka.sensors.avro.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.avro.State;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateDurationSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateStateSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SensorStateDurationReflect;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SensorStateReflect;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

/* package */ final class TestHelper {
  private TestHelper() {}

  /* package */ static List<Object[]> createParameters() {
    var sensorStateStateSchemas =
        List.of(
            State.getClassSchema(),
            SensorStateStateSchema.SCHEMA,
            ReflectData.get().getSchema(SensorStateReflect.State.class));

    var sensorStateSchemas =
        List.of(SensorState.getClassSchema(), SensorStateSchema.SCHEMA, SensorStateReflect.SCHEMA);

    var sensorStateDurationSchemas =
        List.of(
            SensorStateDuration.getClassSchema(),
            SensorStateDurationSchema.SCHEMA,
            SensorStateDurationReflect.SCHEMA);

    return Stream.of(sensorStateStateSchemas, sensorStateSchemas, sensorStateDurationSchemas)
        .flatMap(TestHelper::combinations)
        .toList();
  }

  private static Stream<Object[]> combinations(Collection<Schema> schemata) {
    return schemata.stream().map(schema -> new Object[] {schema.getFullName(), schema, schemata});
  }
}
