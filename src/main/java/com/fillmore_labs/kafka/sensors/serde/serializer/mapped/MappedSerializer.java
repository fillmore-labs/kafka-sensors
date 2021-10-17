package com.fillmore_labs.kafka.sensors.serde.serializer.mapped;

import java.util.Map;
import java.util.function.Function;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.Nullable;

/* package */ final class MappedSerializer<U, T> implements Serializer<U> {
  private final Serializer<T> serializer;
  private final Function<@Nullable U, @Nullable T> unmapper;

  /* package */ MappedSerializer(
      Serializer<T> serializer, Function<@Nullable U, @Nullable T> unmapper) {
    this.serializer = serializer;
    this.unmapper = unmapper;
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    serializer.configure(configs, isKey);
  }

  @Override
  @SuppressWarnings({
    "nullness:argument",
    "nullness:override.return"
  }) // Serializer is not annotated
  public byte @Nullable [] serialize(String topic, @Nullable U data) {
    return serializer.serialize(topic, unmapper.apply(data));
  }

  @Override
  @SuppressWarnings({
    "nullness:argument",
    "nullness:override.return"
  }) // Serializer is not annotated
  public byte @Nullable [] serialize(String topic, Headers headers, @Nullable U data) {
    return serializer.serialize(topic, headers, unmapper.apply(data));
  }

  @Override
  public void close() {
    serializer.close();
  }
}
