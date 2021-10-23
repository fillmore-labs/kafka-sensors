package com.fillmore_labs.kafka.sensors.serde.json;

import static com.google.common.truth.Truth.assertThat;

import javax.inject.Inject;
import org.apache.kafka.common.serialization.Serde;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Test;

public abstract class SerdeTestBase<T> {
  private static final String KAFKA_TOPIC = "topic";

  @Inject /* package */ @MonotonicNonNull Serde<T> serde;

  private final T standardValue;

  protected SerdeTestBase(T standardValue) {
    this.standardValue = standardValue;
  }

  @Test
  @RequiresNonNull("serde")
  public void compatability() {
    var sensorState = standardValue;

    var encoded = serde.serializer().serialize(KAFKA_TOPIC, sensorState);
    assertThat(encoded).isNotEmpty();

    var decoded = serde.deserializer().deserialize(KAFKA_TOPIC, encoded);
    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  @RequiresNonNull("serde")
  public void nullEncoding() {
    @SuppressWarnings("nullness:argument") // Serializer is not annotated
    var encoded = serde.serializer().serialize(KAFKA_TOPIC, null);
    assertThat(encoded == null || encoded.length == 0).isTrue();
  }

  @Test
  @RequiresNonNull("serde")
  public void nullDecoding() {
    @SuppressWarnings("nullness:argument") // Deserializer is not annotated
    var decoded = serde.deserializer().deserialize(KAFKA_TOPIC, null);
    assertThat(decoded).isNull();
  }

  @Test
  @RequiresNonNull("serde")
  public void emptyDecoding() {
    var decoded = serde.deserializer().deserialize(KAFKA_TOPIC, new byte[0]);
    assertThat(decoded).isNull();
  }
}
