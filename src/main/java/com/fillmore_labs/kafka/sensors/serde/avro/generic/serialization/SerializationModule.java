package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroSerializer;
import com.google.common.base.Optional;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.Provides;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.message.SchemaStore;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Avro.SensorState
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<GenericRecord> sensorStateSerializer() {
    return new AvroSerializer<>(SensorStateSchema.MODEL, SensorStateSchema.SCHEMA);
  }

  @Provides
  @Avro.SensorState
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<GenericRecord> sensorStateDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        SensorStateSchema.MODEL, SensorStateSchema.SCHEMA, resolver.orNull());
  }

  @Provides
  @Avro.SensorStateDuration
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<GenericRecord> sensorStateDurationSerializer() {
    return new AvroSerializer<>(SensorStateDurationSchema.MODEL, SensorStateDurationSchema.SCHEMA);
  }

  @Provides
  @Avro.SensorStateDuration
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<GenericRecord> sensorStateDurationDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        SensorStateDurationSchema.MODEL, SensorStateDurationSchema.SCHEMA, resolver.orNull());
  }

  @BindsOptionalOf
  /* package */ abstract SchemaStore schemaStore();
}
