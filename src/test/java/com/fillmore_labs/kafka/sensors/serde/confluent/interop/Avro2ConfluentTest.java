package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.interop.context.TestComponent;
import com.fillmore_labs.kafka.sensors.serde.confluent.interop.context.TestComponent.SingleTestComponent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.util.NullnessUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class Avro2ConfluentTest {
  private final Avro2Confluent recoder;
  private final Serializer<StateDuration> serializer;
  private final Deserializer<StateDuration> deserializer;

  public Avro2ConfluentTest(String description, SingleTestComponent singleTestComponent) {
    this.recoder = singleTestComponent.avro2Confluent();
    this.serializer = singleTestComponent.serializer();
    this.deserializer = singleTestComponent.deserializer();
  }

  @Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> parameters() {
    return TestComponent.create().avro2ConfluentParameters();
  }

  @Test
  public void testReverseInterop() {
    var sensorState = TestHelper.standardStateDuration();

    var encoded = serializer.serialize(TestHelper.KAFKA_TOPIC, sensorState);

    // Check for single-record format (version 1) marker
    // https://avro.apache.org/docs/current/spec.html#single_object_encoding
    assertThat(encoded[0]).isEqualTo((byte) 0xc3);
    assertThat(encoded[1]).isEqualTo((byte) 0x01);

    var reencoded = NullnessUtil.castNonNull(recoder.transform(TestHelper.KAFKA_TOPIC, encoded));

    // Check for “Magic Byte”
    // https://docs.confluent.io/current/schema-registry/serializer-formatter.html#wire-format
    assertThat(reencoded[0]).isEqualTo((byte) 0);

    var decoded = deserializer.deserialize(TestHelper.KAFKA_TOPIC, reencoded);
    assertThat(decoded).isEqualTo(sensorState);
  }
}
