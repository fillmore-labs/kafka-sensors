package com.fillmore_labs.kafka.sensors.serde.serializer.mapped;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

public final class MappedSerdes {
  private MappedSerdes() {}

  public static <S, T> Serde<S> serdeFrom(
      Serializer<T> serializer, Deserializer<T> deserializer, BiMapper<S, T> mapper) {
    var mappedSerializer = new MappedSerializer<>(serializer, mapper::map);
    var mappedDeserializer = new MappedDeserializer<>(deserializer, mapper::unmap);
    return Serdes.serdeFrom(mappedSerializer, mappedDeserializer);
  }
}
