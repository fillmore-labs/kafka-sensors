package com.fillmore_labs.kafka.sensors.serde.thrift.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.thrift.protocol.TProtocolException;
import org.junit.Test;

public final class SerializationTest {
  private static final String TOPIC = "topic";

  private final Serializer<StateDuration> serializer;
  private final Deserializer<StateDuration> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializer();
    this.deserializer = testComponent.deserializer();
  }

  @Test
  public void canDecode() {
    var sensorState = TestHelper.createStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);

    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public void notNull() {
    var sensorState = TestHelper.createStateDuration();
    sensorState.reading.unsetPosition();

    var exception =
        assertThrows(SerializationException.class, () -> serializer.serialize(TOPIC, sensorState));
    assertThat(exception).hasCauseThat().isInstanceOf(TProtocolException.class);
  }

  @Test
  public void nullEncoding() {
    @SuppressWarnings("nullness:argument") // Serializer is not annotated
    var encoded = serializer.serialize(TOPIC, null);

    assertThat(encoded).isNull();
  }

  @Test
  public void nullDecoding() {
    @SuppressWarnings("nullness:argument") // Deserializer is not annotated
    var decoded = deserializer.deserialize(TOPIC, null);

    assertThat(decoded).isNull();
  }

  @Test
  public void invalid() {
    var encoded = new byte[] {0x1};
    assertThrows(SerializationException.class, () -> deserializer.deserialize(TOPIC, encoded));
  }
}
