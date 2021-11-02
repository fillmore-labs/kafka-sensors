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
  /* package */ static Serializer<ReadingReflect> readingSerializer() {
    return new AvroSerializer<>(ReadingReflect.MODEL, ReadingReflect.SCHEMA);
  }

  @Provides
  /* package */ static Deserializer<ReadingReflect> readingDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(ReadingReflect.MODEL, ReadingReflect.SCHEMA, resolver.orNull());
  }

  @Provides
  /* package */ static Serializer<SensorStateReflect> sensorStateSerializer() {
    return new AvroSerializer<>(SensorStateReflect.MODEL, SensorStateReflect.SCHEMA);
  }

  @Provides
  /* package */ static Deserializer<SensorStateReflect> sensorStateDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        SensorStateReflect.MODEL, SensorStateReflect.SCHEMA, resolver.orNull());
  }

  @Provides
  /* package */ static Serializer<StateDurationReflect> stateDurationSerializer() {
    return new AvroSerializer<>(StateDurationReflect.MODEL, StateDurationReflect.SCHEMA);
  }

  @Provides
  /* package */ static Deserializer<StateDurationReflect> stateDurationDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        StateDurationReflect.MODEL, StateDurationReflect.SCHEMA, resolver.orNull());
  }

  @BindsOptionalOf
  /* package */ abstract SchemaStore schemaStore();
}
