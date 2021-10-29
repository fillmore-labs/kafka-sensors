package com.fillmore_labs.kafka.sensors.serde.avro.specific.serialization;

import com.fillmore_labs.kafka.sensors.avro.Event;
import com.fillmore_labs.kafka.sensors.avro.SensorState;
import com.fillmore_labs.kafka.sensors.avro.StateDuration;
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
  /* package */ static Serializer<Event> eventSerializer() {
    return new AvroSerializer<>(Event.getEncoder());
  }

  @Provides
  @SuppressWarnings({"CloseableProvides", "nullness:argument"})
  /* package */ static Deserializer<Event> eventDeserializer(Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(Event.createDecoder(resolver.orNull()));
  }

  @Provides
  /* package */ static Serializer<SensorState> sensorStateSerializer() {
    return new AvroSerializer<>(SensorState.getEncoder());
  }

  @Provides
  @SuppressWarnings({"CloseableProvides", "nullness:argument"})
  /* package */ static Deserializer<SensorState> sensorStateDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(SensorState.createDecoder(resolver.orNull()));
  }

  @Provides
  /* package */ static Serializer<StateDuration> stateDurationSerializer() {
    return new AvroSerializer<>(StateDuration.getEncoder());
  }

  @Provides
  @SuppressWarnings({"CloseableProvides", "nullness:argument"})
  /* package */ static Deserializer<StateDuration> stateDurationDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(StateDuration.createDecoder(resolver.orNull()));
  }

  @BindsOptionalOf
  /* package */ abstract SchemaStore schemaStore();
}
