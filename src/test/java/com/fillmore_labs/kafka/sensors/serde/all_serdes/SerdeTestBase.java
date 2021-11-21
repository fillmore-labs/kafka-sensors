package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import static com.google.common.truth.Truth.assertThat;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public /* open */ class SerdeTestBase<T> {
  private static final String KAFKA_TOPIC = "topic";
  private final T standardValue;
  private final Serializer<T> serializer;
  private final Deserializer<T> deserializer;

  protected SerdeTestBase(Serializer<T> serializer, Deserializer<T> deserializer, T standardValue) {
    this.serializer = serializer;
    this.deserializer = deserializer;
    this.standardValue = standardValue;
  }

  @Test
  public final void compatability() {
    var sensorState = standardValue;

    var encoded = serializer.serialize(KAFKA_TOPIC, sensorState);
    assertThat(encoded).isNotEmpty();

    var decoded = deserializer.deserialize(KAFKA_TOPIC, encoded);
    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public final void nullEncoding() {
    @SuppressWarnings("nullness:argument") // Serializer is not annotated
    var encoded = serializer.serialize(KAFKA_TOPIC, null);
    assertThat(encoded == null || encoded.length == 0).isTrue();
  }

  @Test
  public final void nullDecoding() {
    @SuppressWarnings("nullness:argument") // Deserializer is not annotated
    var decoded = deserializer.deserialize(KAFKA_TOPIC, null);
    assertThat(decoded).isNull();
  }

  @Test
  public final void emptyDecoding() {
    var decoded = deserializer.deserialize(KAFKA_TOPIC, new byte[0]);
    assertThat(decoded).isNull();
  }
}
