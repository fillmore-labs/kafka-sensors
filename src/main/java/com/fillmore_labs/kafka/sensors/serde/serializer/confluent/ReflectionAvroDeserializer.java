package com.fillmore_labs.kafka.sensors.serde.serializer.confluent;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import java.nio.BufferUnderflowException;
import java.util.HashMap;
import java.util.Map;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class ReflectionAvroDeserializer<T> implements Deserializer<T> {
  private final Schema schema;
  private final KafkaAvroDeserializer inner;

  public ReflectionAvroDeserializer(Class<T> type) {
    this.schema = ReflectData.get().getSchema(type);
    this.inner = new KafkaAvroDeserializer();
  }

  @Override
  @SuppressWarnings({"nullness:override.param", "nullness:override.return"})
  public void configure(
      @Nullable Map<String, ? extends @NonNull Object> configs,
      boolean isDeserializerForRecordKeys) {
    Map<String, Object> reflectionAvroEnabledConfig =
        configs == null ? new HashMap<>() : new HashMap<>(configs);
    reflectionAvroEnabledConfig.put(KafkaAvroDeserializerConfig.SCHEMA_REFLECTION_CONFIG, true);

    inner.configure(reflectionAvroEnabledConfig, isDeserializerForRecordKeys);
  }

  @Override
  @SuppressWarnings("nullness:override.return")
  public @Nullable T deserialize(String topic, byte @Nullable [] bytes) {
    return deserialize(topic, null, bytes);
  }

  @SuppressWarnings({"unchecked", "nullness:argument", "nullness:override.return"})
  @Override
  public @Nullable T deserialize(String topic, @Nullable Headers headers, byte @Nullable [] bytes) {
    try {
      return (T) inner.deserialize(topic, headers, bytes, schema);
    } catch (BufferUnderflowException e) {
      var message = String.format("Error while parsing Confluent Avro from topic \"%s\"", topic);
      throw new SerializationException(message, e);
    }
  }

  @Override
  public void close() {
    inner.close();
  }
}
