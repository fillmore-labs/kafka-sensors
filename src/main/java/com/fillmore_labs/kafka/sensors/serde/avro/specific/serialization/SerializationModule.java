package com.fillmore_labs.kafka.sensors.serde.avro.specific.serialization;

import com.fillmore_labs.kafka.sensors.avro.SensorState;
import com.fillmore_labs.kafka.sensors.avro.SensorStateDuration;
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
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateDuration> sensorStateDurationSerializer() {
    return new AvroSerializer<>(SensorStateDuration.getEncoder());
  }

  @Provides
  @SuppressWarnings({"CloseableProvides", "nullness:argument"})
  /* package */ static Deserializer<SensorStateDuration> sensorStateDurationDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(SensorStateDuration.createDecoder(resolver.orNull()));
  }

  @BindsOptionalOf
  /* package */ abstract SchemaStore schemaStore();
}
