package com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.ReadingSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.StateDurationSchema;
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
  @Confluent.Reading
  /* package */ static Serializer<GenericRecord> readingSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var serializer = new GenericAvroSerializer();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  @Confluent.Reading
  /* package */ static Deserializer<GenericRecord> readingDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var deserializer = new GenericAvroDeserializer(ReadingSchema.SCHEMA);
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }

  @Provides
  @Confluent.SensorState
  /* package */ static Serializer<GenericRecord> sensorStateSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var serializer = new GenericAvroSerializer();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  @Confluent.SensorState
  /* package */ static Deserializer<GenericRecord> sensorStateDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var deserializer = new GenericAvroDeserializer(SensorStateSchema.SCHEMA);
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }

  @Provides
  @Confluent.StateDuration
  /* package */ static Serializer<GenericRecord> stateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var serializer = new GenericAvroSerializer();
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  @Provides
  @Confluent.StateDuration
  /* package */ static Deserializer<GenericRecord> stateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    var config = serializerConfig(registryUrl);

    var deserializer = new GenericAvroDeserializer(StateDurationSchema.SCHEMA);
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
