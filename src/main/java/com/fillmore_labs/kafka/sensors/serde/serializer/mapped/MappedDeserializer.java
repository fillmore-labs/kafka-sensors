package com.fillmore_labs.kafka.sensors.serde.serializer.mapped;

import java.util.Map;
import java.util.function.Function;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.checkerframework.checker.nullness.qual.Nullable;

/* package */ final class MappedDeserializer<U, T> implements Deserializer<U> {
  private final Deserializer<T> deserializer;
  private final Function<@Nullable T, @Nullable U> mapper;

  /* package */ MappedDeserializer(
      Deserializer<T> deserializer, Function<@Nullable T, @Nullable U> mapper) {
    this.deserializer = deserializer;
    this.mapper = mapper;
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    deserializer.configure(configs, isKey);
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Deserializer is not annotated
  public @Nullable U deserialize(String topic, byte @Nullable [] data) {
    if (data == null || data.length == 0) {
      return null;
    }
    return mapper.apply(deserializer.deserialize(topic, data));
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Deserializer is not annotated
  public @Nullable U deserialize(String topic, Headers headers, byte @Nullable [] data) {
    if (data == null || data.length == 0) {
      return null;
    }
    return mapper.apply(deserializer.deserialize(topic, headers, data));
  }

  @Override
  public void close() {
    deserializer.close();
  }
}
