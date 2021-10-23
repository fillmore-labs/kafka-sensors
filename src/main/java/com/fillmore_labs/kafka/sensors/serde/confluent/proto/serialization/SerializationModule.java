package com.fillmore_labs.kafka.sensors.serde.confluent.proto.serialization;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.v1.SensorState;
import com.fillmore_labs.kafka.sensors.v1.SensorStateDuration;
import dagger.Module;
import dagger.Provides;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializerConfig;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Confluent
  /* package */ static Serializer<SensorState> sensorStateSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var serializer = new KafkaProtobufSerializer<SensorState>();
    serializer.configure(config, /* isKey= */ false);
    return serializer;
  }

  @Provides
  @Confluent
  /* package */ static Deserializer<SensorState> sensorStateDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config =
        Map.of(
            SCHEMA_REGISTRY_URL_CONFIG,
            registryUrl,
            KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE,
            SensorState.class);
    var deserializer = new KafkaProtobufDeserializer<SensorState>();
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }

  @Provides
  @Confluent
  /* package */ static Serializer<SensorStateDuration> sensorStateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    var serializer = new KafkaProtobufSerializer<SensorStateDuration>();
    serializer.configure(config, /* isKey= */ false);
    return serializer;
  }

  @Provides
  @Confluent
  /* package */ static Deserializer<SensorStateDuration> sensorStateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config =
        Map.of(
            SCHEMA_REGISTRY_URL_CONFIG,
            registryUrl,
            KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE,
            SensorStateDuration.class);
    var deserializer = new KafkaProtobufDeserializer<SensorStateDuration>();
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }
}
