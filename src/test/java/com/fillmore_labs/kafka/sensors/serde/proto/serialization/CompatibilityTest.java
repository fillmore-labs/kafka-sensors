package com.fillmore_labs.kafka.sensors.serde.proto.serialization;

import static com.fillmore_labs.kafka.sensors.test.proto.v1.StateDuration.COMMENT_FIELD_NUMBER;
import static com.google.common.truth.extensions.proto.ProtoTruth.assertThat;

import com.fillmore_labs.kafka.sensors.proto.v1.StateDuration;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public class CompatibilityTest {
  private static final String TOPIC = "topic";

  private final Serializer<StateDuration> serializer;
  private final Deserializer<StateDuration> deserializer;
  private final Serializer<com.fillmore_labs.kafka.sensors.test.proto.v1.StateDuration>
      testSerializer;
  private final Deserializer<com.fillmore_labs.kafka.sensors.test.proto.v1.StateDuration>
      testDeserializer;

  public CompatibilityTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializer();
    this.deserializer = testComponent.deserializer();
    this.testSerializer = testComponent.testSerializer();
    this.testDeserializer = testComponent.testDeserializer();
  }

  @Test
  public void testBackwardCompatibility() {
    var sensorState = TestHelper.createTestStateDuration();

    var encoded = testSerializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    var expected = TestHelper.createStateDuration();
    assertThat(decoded).ignoringFieldAbsence().isEqualTo(expected);
  }

  @Test
  public void testForwardCompatibility() {
    var sensorState = TestHelper.createStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = testDeserializer.deserialize(TOPIC, encoded);

    var expected = TestHelper.createTestStateDuration();
    assertThat(decoded).ignoringFields(COMMENT_FIELD_NUMBER).isEqualTo(expected);
  }
}
