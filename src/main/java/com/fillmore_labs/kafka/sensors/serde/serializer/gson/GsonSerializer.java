package com.fillmore_labs.kafka.sensors.serde.serializer.gson;

import com.google.gson.TypeAdapter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class GsonSerializer<T> implements Serializer<T> {
  private final TypeAdapter<T> adapter;

  public GsonSerializer(TypeAdapter<T> adapter) {
    this.adapter = adapter;
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    if (isKey) {
      throw new IllegalArgumentException("JSON encoding is not stable");
    }
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Serializer is not annotated
  public byte @Nullable [] serialize(String topic, @Nullable T message) {
    if (message == null) {
      return null;
    }
    return adapter.toJson(message).getBytes(StandardCharsets.UTF_8);
  }
}
