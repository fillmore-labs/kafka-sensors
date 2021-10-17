package com.fillmore_labs.kafka.sensors.serde.confluent.specific.serialization;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.fillmore_labs.kafka.sensors.avro.SensorState;
import com.fillmore_labs.kafka.sensors.avro.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.serde.serializer.confluent.SpecificAvroDeserializer;
import dagger.Module;
import dagger.Provides;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Confluent
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorState> sensorStateSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var serializer = new SpecificAvroSerializer<SensorState>();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  @Confluent
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorState> sensorStateDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var deserializer = new SpecificAvroDeserializer<>(SensorState.class);
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }

  @Provides
  @Confluent
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateDuration> sensorStateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var serializer = new SpecificAvroSerializer<SensorStateDuration>();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  @Confluent
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateDuration> sensorStateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var deserializer = new SpecificAvroDeserializer<>(SensorStateDuration.class);
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }
}
