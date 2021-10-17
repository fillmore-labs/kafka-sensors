package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.confluent.SchemaRegistryRule;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.interop.TestComponent.SingleTestComponent;
import java.util.List;
import java.util.function.Supplier;
import javax.inject.Inject;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.checker.nullness.util.NullnessUtil;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class Avro2ConfluentTest {
  @ClassRule
  public static final SchemaRegistryRule REGISTRY_TEST_RESOURCE = new SchemaRegistryRule();

  @Inject /* package */ @MonotonicNonNull Recoder recoder;
  @Inject /* package */ @MonotonicNonNull Serializer<SensorStateDuration> serializer;
  @Inject /* package */ @MonotonicNonNull Deserializer<SensorStateDuration> deserializer;

  @SuppressWarnings("nullness:argument")
  public Avro2ConfluentTest(String description, Supplier<SingleTestComponent> componentSupplier) {
    var component = componentSupplier.get();
    component.inject(this);
  }

  @Parameters(name = "{index}: {0}")
  public static List<Object[]> parameters() {
    return TestHelper.reverseParametersWithDuration(REGISTRY_TEST_RESOURCE.registryUrl());
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer", "recoder"})
  public void testReverseInterop() {
    var sensorState = TestHelper.standardSensorStateDuration();

    var encoded = serializer.serialize(TestHelper.KAFKA_TOPIC, sensorState);

    // Check for single-record format (version 1) marker
    // https://avro.apache.org/docs/current/spec.html#single_object_encoding
    assertThat(encoded[0]).isEqualTo((byte) 0xc3);
    assertThat(encoded[1]).isEqualTo((byte) 0x01);

    var reencoded = NullnessUtil.castNonNull(recoder.avro2Confluent(encoded));

    // Check for “Magic Byte”
    // https://docs.confluent.io/current/schema-registry/serializer-formatter.html#wire-format
    assertThat(reencoded[0]).isEqualTo((byte) 0);

    var decoded = deserializer.deserialize(TestHelper.KAFKA_TOPIC, reencoded);
    assertThat(decoded).isEqualTo(sensorState);
  }
}
