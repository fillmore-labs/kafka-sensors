package com.fillmore_labs.kafka.sensors.serde.serializer.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class JsonSerializer<T> implements Serializer<T> {
  private final ObjectWriter objectWriter;

  public JsonSerializer(ObjectMapper objectMapper, Class<T> type) {
    this.objectWriter = objectMapper.writerFor(type);
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Serializer is not annotated
  public byte @Nullable [] serialize(String topic, @Nullable T message) {
    if (message == null) {
      return null;
    }
    try {
      return objectWriter.writeValueAsBytes(message);
    } catch (IOException e) {
      var msg = String.format("Error while writing JSON %s for topic \"%s\"", message, topic);
      throw new SerializationException(msg, e);
    }
  }
}
