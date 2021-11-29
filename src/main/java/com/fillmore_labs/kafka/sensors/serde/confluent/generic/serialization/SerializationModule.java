package com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.ReadingSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.StateDurationSchema;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.serde.serializer.confluent.GenericAvroDeserializer;
import dagger.Module;
import dagger.Provides;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerializer;
import java.util.Map;
import org.apache.avro.Schema;
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
    return createSerializer(registryUrl);
  }

  @Provides
  @Confluent.Reading
  /* package */ static Deserializer<GenericRecord> readingDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    return createDeserializer(ReadingSchema.SCHEMA, registryUrl);
  }

  @Provides
  @Confluent.SensorState
  /* package */ static Serializer<GenericRecord> sensorStateSerializer(
      @SchemaRegistryUrl String registryUrl) {
    return createSerializer(registryUrl);
  }

  @Provides
  @Confluent.SensorState
  /* package */ static Deserializer<GenericRecord> sensorStateDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    return createDeserializer(SensorStateSchema.SCHEMA, registryUrl);
  }

  @Provides
  @Confluent.StateDuration
  /* package */ static Serializer<GenericRecord> stateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    return createSerializer(registryUrl);
  }

  @Provides
  @Confluent.StateDuration
  /* package */ static Deserializer<GenericRecord> stateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    return createDeserializer(StateDurationSchema.SCHEMA, registryUrl);
  }

  public static Serializer<GenericRecord> createSerializer(String registryUrl) {
    var serializer = new GenericAvroSerializer();
    var config = Map.of(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    serializer.configure(config, /* isSerializerForRecordKeys= */ false);
    return serializer;
  }

  public static Deserializer<GenericRecord> createDeserializer(Schema schema, String registryUrl) {
    var deserializer = new GenericAvroDeserializer(schema);
    var config = Map.of(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, registryUrl);
    deserializer.configure(config, /* isKey= */ false);
    return deserializer;
  }
}
