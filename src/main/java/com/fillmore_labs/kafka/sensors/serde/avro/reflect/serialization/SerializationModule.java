package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroSerializer;
import com.google.common.base.Optional;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.Provides;
import org.apache.avro.message.SchemaStore;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateReflect> sensorStateSerializer() {
    return new AvroSerializer<>(SensorStateReflect.MODEL, SensorStateReflect.SCHEMA);
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateReflect> sensorStateDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        SensorStateReflect.MODEL, SensorStateReflect.SCHEMA, resolver.orNull());
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateDurationReflect> sensorStateDurationSerializer() {
    return new AvroSerializer<>(
        SensorStateDurationReflect.MODEL, SensorStateDurationReflect.SCHEMA);
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateDurationReflect> sensorStateDurationDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        SensorStateDurationReflect.MODEL, SensorStateDurationReflect.SCHEMA, resolver.orNull());
  }

  @BindsOptionalOf
  /* package */ abstract SchemaStore schemaStore();
}
