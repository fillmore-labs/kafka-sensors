package com.fillmore_labs.kafka.sensors.serde.confluent.json.serialization;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.ReadingJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.StateWithDurationJson;
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
  /* package */ static Serializer<ReadingJson> readingSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);

    var serializer = new KafkaJsonSchemaSerializer<ReadingJson>();
    serializer.configure(config, /* isKey= */ false);

    return serializer;
  }

  @Provides
  @Confluent
  /* package */ static Deserializer<ReadingJson> readingDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config =
        Map.of(
            SCHEMA_REGISTRY_URL_CONFIG,
            registryUrl,
            KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE,
            ReadingJson.class);

    var deserializer = new KafkaJsonSchemaDeserializer<ReadingJson>();
    deserializer.configure(config, /* isKey= */ false);

    return deserializer;
  }

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
  /* package */ static Serializer<StateWithDurationJson> stateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);

    var serializer = new KafkaJsonSchemaSerializer<StateWithDurationJson>();
    serializer.configure(config, /* isKey= */ false);

    return serializer;
  }

  @Provides
  @Confluent
  /* package */ static Deserializer<StateWithDurationJson> stateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config =
        Map.of(
            SCHEMA_REGISTRY_URL_CONFIG,
            registryUrl,
            KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE,
            StateWithDurationJson.class);

    var deserializer = new KafkaJsonSchemaDeserializer<StateWithDurationJson>();
    deserializer.configure(config, /* isKey= */ false);

    return deserializer;
  }
}
