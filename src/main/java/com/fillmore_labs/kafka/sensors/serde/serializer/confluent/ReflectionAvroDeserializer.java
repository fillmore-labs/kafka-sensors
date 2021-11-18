package com.fillmore_labs.kafka.sensors.serde.serializer.confluent;

import java.nio.BufferUnderflowException;
import org.apache.kafka.common.errors.SerializationException;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class ReflectionAvroDeserializer<T>
    extends io.confluent.kafka.streams.serdes.avro.ReflectionAvroDeserializer<T> {
  public ReflectionAvroDeserializer(Class<T> type) {
    super(type);
  }

  @Override
  @SuppressWarnings({
    "nullness:argument",
    "nullness:override.return"
  }) // ReflectionAvroDeserializer is not annotated
  public @Nullable T deserialize(String topic, byte @Nullable [] bytes) {
    try {
      return super.deserialize(topic, bytes);
    } catch (BufferUnderflowException e) {
      var message = String.format("Error while parsing Confluent Avro from topic \"%s\"", topic);
      throw new SerializationException(message, e);
    }
  }
}
