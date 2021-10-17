package com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateDurationSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.serde.serializer.confluent.GenericAvroDeserializer;
import dagger.Module;
import dagger.Provides;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerializer;
import java.util.Map;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Confluent.SensorState
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<GenericRecord> sensorStateSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var serializer = new GenericAvroSerializer();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  @Confluent.SensorState
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<GenericRecord> sensorStateDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var deserializer = new GenericAvroDeserializer(SensorStateSchema.SCHEMA);
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }

  @Provides
  @Confluent.SensorStateDuration
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<GenericRecord> sensorStateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var serializer = new GenericAvroSerializer();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  @Confluent.SensorStateDuration
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<GenericRecord> sensorStateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var deserializer = new GenericAvroDeserializer(SensorStateDurationSchema.SCHEMA);
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }

  private static Map<String, ?> serializerConfig(String registryUrl) {
    return Map.of(
        AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        registryUrl,
        KafkaAvroSerializerConfig.AVRO_USE_LOGICAL_TYPE_CONVERTERS_CONFIG,
        true);
  }
}
