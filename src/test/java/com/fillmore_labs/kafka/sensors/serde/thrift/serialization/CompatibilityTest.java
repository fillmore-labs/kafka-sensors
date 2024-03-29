package com.fillmore_labs.kafka.sensors.serde.thrift.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public final class CompatibilityTest {
  private static final String TOPIC = "topic";

  private final Serializer<StateDuration> serializer;
  private final Deserializer<StateDuration> deserializer;
  private final Serializer<com.fillmore_labs.kafka.sensors.test.thrift.v1.StateDuration>
      testSerializer;
  private final Deserializer<com.fillmore_labs.kafka.sensors.test.thrift.v1.StateDuration>
      testDeserializer;

  public CompatibilityTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializer();
    this.deserializer = testComponent.deserializer();
    this.testSerializer = testComponent.testSerializer();
    this.testDeserializer = testComponent.testDeserializer();
  }

  @Test
  public void backwardCompatibility() {
    var sensorState = TestHelper.createTestStateDuration();

    var encoded = testSerializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    var expected = TestHelper.createStateDuration();
    assertThat(decoded).isEqualTo(expected);
  }

  @Test
  public void forwardCompatibility() {
    var sensorState = TestHelper.createStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = testDeserializer.deserialize(TOPIC, encoded);

    var expected = TestHelper.createTestStateDuration();
    expected.unsetComment();
    assertThat(decoded).isEqualTo(expected);
  }
}
