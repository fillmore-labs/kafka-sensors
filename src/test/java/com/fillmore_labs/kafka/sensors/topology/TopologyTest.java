package com.fillmore_labs.kafka.sensors.topology;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.topology.server.EmbeddedKafka;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.TopologyTestDriver;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

public final class TopologyTest {
  @ClassRule public static final EmbeddedKafka KAFKA_TEST_RESOURCE = new EmbeddedKafka();

  @Inject /* package */ @MonotonicNonNull TopologyTestDriver testDriver;
  @Inject /* package */ @MonotonicNonNull TestInputTopic<String, SensorState> inputTopic;
  @Inject /* package */ @MonotonicNonNull TestOutputTopic<String, SensorStateDuration> resultTopic;

  @Before
  public void before() {
    TestComponent.builder().embeddedKafka(KAFKA_TEST_RESOURCE).build().inject(this);
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
  @RequiresNonNull({"inputTopic", "resultTopic"})
  public void testTopology() {
    var instant = Instant.ofEpochSecond(443634300L);

    var initialState = SensorState.builder().id("7331").time(instant).state(State.OFF).build();

    pipeState(initialState);

    var result1 = resultTopic.readKeyValue();

    assertThat(result1.value).isNull();

    var next = instant.plusSeconds(30);
    var newState = SensorState.builder().id("7331").time(next).state(State.ON).build();

    pipeState(newState);

    var result2 = resultTopic.readKeyValue();

    assertThat(result2.value).isNotNull();
    assertThat(result2.value.getEvent()).isEqualTo(initialState);
    assertThat(result2.value.getDuration()).isEqualTo(Duration.ofSeconds(30));
  }

  @Test
  @RequiresNonNull({"inputTopic", "resultTopic"})
  public void testRepeated() {
    var instant = Instant.ofEpochSecond(443634300L);

    var initialState = SensorState.builder().id("7331").time(instant).state(State.OFF).build();

    pipeState(initialState);

    var result1 = resultTopic.readKeyValue();

    assertThat(result1.value).isNull();

    var next = instant.plusSeconds(30);
    var newState = SensorState.builder().id("7331").time(next).state(State.OFF).build();

    pipeState(newState);

    var result2 = resultTopic.readKeyValue();

    assertThat(result2.value).isNotNull();
    assertThat(result2.value.getEvent()).isEqualTo(initialState);
    assertThat(result2.value.getDuration()).isEqualTo(Duration.ofSeconds(30));

    var next2 = next.plusSeconds(30);
    var newState2 = SensorState.builder().id("7331").time(next2).state(State.ON).build();

    pipeState(newState2);

    var result3 = resultTopic.readKeyValue();

    assertThat(result3.value).isNotNull();
    assertThat(result3.value.getEvent()).isEqualTo(initialState);
    assertThat(result3.value.getDuration()).isEqualTo(Duration.ofSeconds(60));

    var next3 = next2.plusSeconds(15);
    var newState3 = SensorState.builder().id("7331").time(next3).state(State.OFF).build();

    pipeState(newState3);

    var result4 = resultTopic.readKeyValue();

    assertThat(result4.value).isNotNull();
    assertThat(result4.value.getEvent()).isEqualTo(newState2);
    assertThat(result4.value.getDuration()).isEqualTo(Duration.ofSeconds(15));
  }

  @Test
  @RequiresNonNull({"inputTopic", "resultTopic"})
  @SuppressWarnings("nullness:argument") // TestInputTopic is not annotated
  public void testTombstone() {
    inputTopic.pipeInput("7331", null);

    var result = resultTopic.readKeyValue();

    assertThat(result.value).isNull();
  }
}
