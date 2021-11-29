package com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.StateDurationTestSchema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public final class CompatibilityTest {
  private static final String TOPIC = "topic";

  private final Serializer<GenericRecord> serializer;
  private final Deserializer<GenericRecord> deserializer;
  private final Serializer<GenericRecord> testSerializer;
  private final Deserializer<GenericRecord> testDeserializer;

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
    assertThat(decoded).isEqualTo(expected);
  }

  @Test
  @SuppressWarnings("nullness:argument") // GenericRecord is not annotated
  public void testForwardCompatibility() {
    var sensorState = TestHelper.createStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = testDeserializer.deserialize(TOPIC, encoded);

    var expected = TestHelper.createTestStateDuration();
    expected.put(StateDurationTestSchema.FIELD_COMMENT, null);
    assertThat(decoded).isEqualTo(expected);
  }
}
