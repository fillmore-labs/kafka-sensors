package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import dagger.Module;
import dagger.Provides;
import io.confluent.kafka.streams.serdes.avro.ReflectionAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.ReflectionAvroSerializer;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  /* package */ static Serializer<ReadingReflect> readingSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var serializer = new ReflectionAvroSerializer<ReadingReflect>();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  /* package */ static Deserializer<ReadingReflect> readingDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var deserializer = new ReflectionAvroDeserializer<>(ReadingReflect.class);
    deserializer.configure(config, /* isDeserializerForRecordKeys= */ false);
    return deserializer;
  }

  @Provides
  /* package */ static Serializer<SensorStateReflect> sensorStateSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var serializer = new ReflectionAvroSerializer<SensorStateReflect>();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  /* package */ static Deserializer<SensorStateReflect> sensorStateDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var deserializer = new ReflectionAvroDeserializer<>(SensorStateReflect.class);
    deserializer.configure(config, /* isDeserializerForRecordKeys= */ false);
    return deserializer;
  }

  @Provides
  /* package */ static Serializer<StateDurationReflect> stateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var serializer = new ReflectionAvroSerializer<StateDurationReflect>();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  /* package */ static Deserializer<StateDurationReflect> stateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var deserializer = new ReflectionAvroDeserializer<>(StateDurationReflect.class);
    deserializer.configure(config, /* isDeserializerForRecordKeys= */ false);
    return deserializer;
  }
}
