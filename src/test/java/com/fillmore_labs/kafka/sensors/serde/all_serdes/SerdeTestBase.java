package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import static com.google.common.truth.Truth.assertThat;

import javax.inject.Inject;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public abstract class SerdeTestBase<T> {
  private static final String KAFKA_TOPIC = "topic";

  @Inject /* package */ Serializer<T> serializer;
  @Inject /* package */ Deserializer<T> deserializer;

  private final T standardValue;

  protected SerdeTestBase(Injector<SerdeTestBase<T>> injector, T standardValue) {
    injector.injectMembers(this);
    this.standardValue = standardValue;
    assert serializer != null : "@AssumeAssertion(nullness): inject() failed";
    assert deserializer != null : "@AssumeAssertion(nullness): inject() failed";
  }

  @Test
  public void compatability() {
    var sensorState = standardValue;

    var encoded = serializer.serialize(KAFKA_TOPIC, sensorState);
    assertThat(encoded).isNotEmpty();

    var decoded = deserializer.deserialize(KAFKA_TOPIC, encoded);
    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public void nullEncoding() {
    @SuppressWarnings("nullness:argument") // Serializer is not annotated
    var encoded = serializer.serialize(KAFKA_TOPIC, null);
    assertThat(encoded == null || encoded.length == 0).isTrue();
  }

  @Test
  public void nullDecoding() {
    @SuppressWarnings("nullness:argument") // Deserializer is not annotated
    var decoded = deserializer.deserialize(KAFKA_TOPIC, null);
    assertThat(decoded).isNull();
  }

  @Test
  public void emptyDecoding() {
    var decoded = deserializer.deserialize(KAFKA_TOPIC, new byte[0]);
    assertThat(decoded).isNull();
  }
}
