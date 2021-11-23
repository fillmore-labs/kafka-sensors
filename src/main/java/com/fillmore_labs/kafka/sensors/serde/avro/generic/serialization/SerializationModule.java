package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroSerializer;
import com.google.common.base.Optional;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.message.SchemaStore;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Avro.Reading
  /* package */ static Serializer<GenericRecord> readingSerializer() {
    return new AvroSerializer<>(ReadingSchema.MODEL, ReadingSchema.SCHEMA);
  }

  @Provides
  @Avro.Reading
  /* package */ static Deserializer<GenericRecord> readingDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(ReadingSchema.MODEL, ReadingSchema.SCHEMA, resolver.orNull());
  }

  @Provides
  @IntoSet
  /* package */ static Schema readingSchema() {
    return ReadingSchema.SCHEMA;
  }

  @Provides
  @Avro.SensorState
  /* package */ static Serializer<GenericRecord> sensorStateSerializer() {
    return new AvroSerializer<>(SensorStateSchema.MODEL, SensorStateSchema.SCHEMA);
  }

  @Provides
  @Avro.SensorState
  /* package */ static Deserializer<GenericRecord> sensorStateDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        SensorStateSchema.MODEL, SensorStateSchema.SCHEMA, resolver.orNull());
  }

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateSchema() {
    return SensorStateSchema.SCHEMA;
  }

  @Provides
  @Avro.StateDuration
  /* package */ static Serializer<GenericRecord> stateDurationSerializer() {
    return new AvroSerializer<>(StateDurationSchema.MODEL, StateDurationSchema.SCHEMA);
  }

  @Provides
  @Avro.StateDuration
  /* package */ static Deserializer<GenericRecord> stateDurationDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        StateDurationSchema.MODEL, StateDurationSchema.SCHEMA, resolver.orNull());
  }

  @Provides
  @IntoSet
  /* package */ static Schema stateDurationSchema() {
    return StateDurationSchema.SCHEMA;
  }

  @BindsOptionalOf
  /* package */ abstract SchemaStore schemaStore();
}
