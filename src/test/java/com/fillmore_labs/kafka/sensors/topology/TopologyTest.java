package com.fillmore_labs.kafka.sensors.topology;

import static com.fillmore_labs.kafka.sensors.topology.TopologyTestHelper.INPUT_TOPIC;
import static com.fillmore_labs.kafka.sensors.topology.TopologyTestHelper.RESULT_TOPIC;
import static com.fillmore_labs.kafka.sensors.topology.TopologyTestHelper.newKafkaTestResource;
import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.confluent.SchemaRegistryRule;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.topology.context.ParameterComponent;
import com.fillmore_labs.kafka.sensors.topology.context.TopologyComponent;
import com.fillmore_labs.kafka.sensors.topology.server.EmbeddedKafka;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.TopologyTestDriver;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class TopologyTest {
  @ClassRule public static final EmbeddedKafka KAFKA_TEST_RESOURCE = newKafkaTestResource();

  @ClassRule
  public static final SchemaRegistryRule REGISTRY_TEST_RESOURCE =
      new SchemaRegistryRule("topology");

  private final Serde<SensorState> inputSerde;
  private final Serde<SensorState> storeSerde;
  private final Serde<SensorStateDuration> resultSerde;
  private @MonotonicNonNull TopologyTestDriver testDriver;
  private @MonotonicNonNull TestInputTopic<String, SensorState> inputTopic;
  private @MonotonicNonNull TestOutputTopic<String, SensorStateDuration> outputTopic;

  public TopologyTest(
      String description,
      Serde<SensorState> inputSerde,
      Serde<SensorState> storeSerde,
      Serde<SensorStateDuration> resultSerde) {
    this.inputSerde = inputSerde;
    this.storeSerde = storeSerde;
    this.resultSerde = resultSerde;
  }

  @Parameters(name = "{index}: {0}")
  public static Iterable<?> parameters() {
    var parameterComponent =
        ParameterComponent.builder()
            .schemaRegistryUrl(REGISTRY_TEST_RESOURCE.registryUrl())
            .build();
    var parameters = parameterComponent.parameters();
    return parameters.get();
  }

  @Before
  @EnsuresNonNull({"testDriver", "inputTopic", "outputTopic"})
  public void before() {
    var configuration = TopologyTestHelper.configuration(KAFKA_TEST_RESOURCE);
    var settings = TopologyTestHelper.settings(KAFKA_TEST_RESOURCE);
    var testComponent =
        TopologyComponent.builder()
            .configuration(configuration)
            .inputSerde(inputSerde)
            .storeSerde(storeSerde)
            .resultSerde(resultSerde)
            .settings(settings)
            .build();

    testDriver = testComponent.topologyTestDriver();

    inputTopic =
        testDriver.createInputTopic(INPUT_TOPIC, new StringSerializer(), inputSerde.serializer());
    outputTopic =
        testDriver.createOutputTopic(
            RESULT_TOPIC, new StringDeserializer(), resultSerde.deserializer());
  }

  @After
  @RequiresNonNull("testDriver")
  public void after() {
    testDriver.close();
  }

  @RequiresNonNull("inputTopic")
  private void pipeState(SensorState sensorState) {
    inputTopic.pipeInput(sensorState.getId(), sensorState);
  }

  @Test
  @RequiresNonNull({"inputTopic", "outputTopic"})
  public void testTopology() {
    var instant = Instant.ofEpochSecond(443634300L);

    var initialState = SensorState.builder().id("7331").time(instant).state(State.OFF).build();

    pipeState(initialState);

    var result1 = outputTopic.readKeyValue();

    assertThat(result1.value).isNull();

    var next = instant.plusSeconds(30);
    var newState = SensorState.builder().id("7331").time(next).state(State.ON).build();

    pipeState(newState);

    var result2 = outputTopic.readKeyValue();

    assertThat(result2.value).isNotNull();
    assertThat(result2.value.getEvent()).isEqualTo(initialState);
    assertThat(result2.value.getDuration()).isEqualTo(Duration.ofSeconds(30));
  }

  @Test
  @RequiresNonNull({"inputTopic", "outputTopic"})
  public void testRepeated() {
    var instant = Instant.ofEpochSecond(443634300L);

    var initialState = SensorState.builder().id("7331").time(instant).state(State.OFF).build();

    pipeState(initialState);

    var result1 = outputTopic.readKeyValue();

    assertThat(result1.value).isNull();

    var next = instant.plusSeconds(30);
    var newState = SensorState.builder().id("7331").time(next).state(State.OFF).build();

    pipeState(newState);

    var result2 = outputTopic.readKeyValue();

    assertThat(result2.value).isNotNull();
    assertThat(result2.value.getEvent()).isEqualTo(initialState);
    assertThat(result2.value.getDuration()).isEqualTo(Duration.ofSeconds(30));

    var next2 = next.plusSeconds(30);
    var newState2 = SensorState.builder().id("7331").time(next2).state(State.ON).build();

    pipeState(newState2);

    var result3 = outputTopic.readKeyValue();

    assertThat(result3.value).isNotNull();
    assertThat(result3.value.getEvent()).isEqualTo(initialState);
    assertThat(result3.value.getDuration()).isEqualTo(Duration.ofSeconds(60));

    var next3 = next2.plusSeconds(15);
    var newState3 = SensorState.builder().id("7331").time(next3).state(State.OFF).build();

    pipeState(newState3);

    var result4 = outputTopic.readKeyValue();

    assertThat(result4.value).isNotNull();
    assertThat(result4.value.getEvent()).isEqualTo(newState2);
    assertThat(result4.value.getDuration()).isEqualTo(Duration.ofSeconds(15));
  }

  @Test
  @RequiresNonNull({"inputTopic", "outputTopic"})
  @SuppressWarnings("nullness:argument") // TestInputTopic is not annotated
  public void testTombstone() {
    inputTopic.pipeInput("7331", null);

    var result = outputTopic.readKeyValue();

    assertThat(result.value).isNull();
  }
}
