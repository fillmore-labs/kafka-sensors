package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroSerializer;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import java.util.Optional;
import org.apache.avro.Schema;
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
    return new AvroDeserializer<>(
        ReadingReflect.MODEL, ReadingReflect.SCHEMA, resolver.orElse(null));
  }

  @Provides
  @IntoSet
  /* package */ static Schema readingSchema() {
    return ReadingReflect.SCHEMA;
  }

  @Provides
  /* package */ static Serializer<SensorStateReflect> sensorStateSerializer() {
    return new AvroSerializer<>(SensorStateReflect.MODEL, SensorStateReflect.SCHEMA);
  }

  @Provides
  /* package */ static Deserializer<SensorStateReflect> sensorStateDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        SensorStateReflect.MODEL, SensorStateReflect.SCHEMA, resolver.orElse(null));
  }

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateSchema() {
    return SensorStateReflect.SCHEMA;
  }

  @Provides
  /* package */ static Serializer<StateDurationReflect> stateDurationSerializer() {
    return new AvroSerializer<>(StateDurationReflect.MODEL, StateDurationReflect.SCHEMA);
  }

  @Provides
  /* package */ static Deserializer<StateDurationReflect> stateDurationDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        StateDurationReflect.MODEL, StateDurationReflect.SCHEMA, resolver.orElse(null));
  }

  @Provides
  @IntoSet
  /* package */ static Schema stateDurationSchema() {
    return StateDurationReflect.SCHEMA;
  }

  @BindsOptionalOf
  /* package */ abstract SchemaStore schemaStore();
}
