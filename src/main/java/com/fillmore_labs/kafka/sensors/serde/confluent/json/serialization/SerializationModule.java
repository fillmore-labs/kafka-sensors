package com.fillmore_labs.kafka.sensors.serde.confluent.json.serialization;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import dagger.Module;
import dagger.Provides;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Confluent
  /* package */ static Serializer<SensorStateJson> sensorStateSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);

    var serializer = new KafkaJsonSchemaSerializer<SensorStateJson>();
    serializer.configure(config, /* isKey= */ false);

    return serializer;
  }

  @Provides
  @Confluent
  /* package */ static Deserializer<SensorStateJson> sensorStateDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config =
        Map.of(
            SCHEMA_REGISTRY_URL_CONFIG,
            registryUrl,
            KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE,
            SensorStateJson.class);

    var deserializer = new KafkaJsonSchemaDeserializer<SensorStateJson>();
    deserializer.configure(config, /* isKey= */ false);

    return deserializer;
  }

  @Provides
  @Confluent
  /* package */ static Serializer<SensorStateDurationJson> sensorStateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);

    var serializer = new KafkaJsonSchemaSerializer<SensorStateDurationJson>();
    serializer.configure(config, /* isKey= */ false);

    return serializer;
  }

  @Provides
  @Confluent
  /* package */ static Deserializer<SensorStateDurationJson> sensorStateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config =
        Map.of(
            SCHEMA_REGISTRY_URL_CONFIG,
            registryUrl,
            KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE,
            SensorStateDurationJson.class);

    var deserializer = new KafkaJsonSchemaDeserializer<SensorStateDurationJson>();
    deserializer.configure(config, /* isKey= */ false);

    return deserializer;
  }
}
