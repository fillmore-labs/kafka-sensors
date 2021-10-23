package com.fillmore_labs.kafka.sensors.serde.serializer.gson_faster;

import com.google.gson.TypeAdapter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class GsonFastDeserializer<T> implements Deserializer<T> {
  private final TypeAdapter<T> adapter;

  public GsonFastDeserializer(TypeAdapter<T> adapter) {
    this.adapter = adapter;
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Deserializer is not annotated
  public @Nullable T deserialize(String topic, byte @Nullable [] data) {
    if (data == null || data.length == 0) {
      return null;
    }
    try {
      var json = new String(data, StandardCharsets.UTF_8);
      return adapter.fromJson(json);
    } catch (IOException | IllegalArgumentException e) {
      var message = String.format("Error while parsing GSON from topic \"%s\"", topic);
      throw new SerializationException(message, e);
    }
  }
}
