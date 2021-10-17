package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.confluent.SchemaRegistryRule;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import java.util.List;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class SerdeTest {
  @ClassRule
  public static final SchemaRegistryRule REGISTRY_TEST_RESOURCE = new SchemaRegistryRule();

  private final Serializer<SensorState> serializer;
  private final Deserializer<SensorState> deserializer;

  public SerdeTest(
      String description, Serde<SensorState> inputSerde, Serde<SensorState> resultSerde) {
    this.serializer = inputSerde.serializer();
    this.deserializer = resultSerde.deserializer();
  }

  @Parameters(name = "{index}: {0}")
  public static List<Object[]> parameters() {
    return TestHelper.parameters(REGISTRY_TEST_RESOURCE.registryUrl());
  }

  @After
  public void after() {
    serializer.close();
    deserializer.close();
  }

  @Test
  public void compatability() {
    var sensorState = TestHelper.standardSensorState();

    var encoded = serializer.serialize(TestHelper.KAFKA_TOPIC, sensorState);
    assertThat(encoded).isNotEmpty();

    var decoded = deserializer.deserialize(TestHelper.KAFKA_TOPIC, encoded);
    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public void nullEncoding() {
    @SuppressWarnings("nullness:argument") // Serializer is not annotated
    var encoded = serializer.serialize(TestHelper.KAFKA_TOPIC, null);
    assertThat(encoded == null || encoded.length == 0).isTrue();
  }

  @Test
  public void nullDecoding() {
    @SuppressWarnings("nullness:argument") // Deserializer is not annotated
    var decoded = deserializer.deserialize(TestHelper.KAFKA_TOPIC, null);
    assertThat(decoded).isNull();
  }

  @Test
  public void emptyDecoding() {
    var decoded = deserializer.deserialize(TestHelper.KAFKA_TOPIC, new byte[0]);
    assertThat(decoded).isNull();
  }
}
