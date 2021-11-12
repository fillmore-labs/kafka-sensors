package com.fillmore_labs.kafka.sensors.serde.serializer.avro;

import java.io.IOException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.MessageDecoder;
import org.apache.avro.message.SchemaStore;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class AvroDeserializer<T> implements Deserializer<T> {
  private final MessageDecoder<T> decoder;

  public AvroDeserializer(MessageDecoder<T> decoder) {
    this.decoder = decoder;
  }

  @SuppressWarnings("nullness:argument")
  public AvroDeserializer(
      GenericData model, @Nullable Schema readSchema, @Nullable SchemaStore resolver) {
    this(new BinaryMessageDecoder<>(model, readSchema, resolver));
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Deserializer is not annotated
  public @Nullable T deserialize(String topic, byte @Nullable [] data) {
    if (data == null || data.length == 0) {
      return null;
    }
    try {
      return decoder.decode(data);
    } catch (IOException | AvroRuntimeException e) {
      var message = String.format("Error while parsing Avro from topic \"%s\"", topic);
      throw new SerializationException(message, e);
    }
  }
}
