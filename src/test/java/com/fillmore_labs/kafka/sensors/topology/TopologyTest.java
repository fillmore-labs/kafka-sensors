package com.fillmore_labs.kafka.sensors.topology;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.Reading.Position;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.topology.context.SingleTestComponent;
import com.fillmore_labs.kafka.sensors.topology.context.TestComponent;
import com.fillmore_labs.kafka.sensors.topology.server.EmbeddedKafka;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.errors.StreamsException;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.AfterParam;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class TopologyTest {
  @ClassRule public static final EmbeddedKafka KAFKA_TEST_RESOURCE = new EmbeddedKafka();
  private final TestInputTopic<String, SensorState> inputTopic;
  private final TestOutputTopic<String, StateDuration> resultTopic;

  public TopologyTest(String description, SingleTestComponent singleTestComponent) {
    this.inputTopic = singleTestComponent.inputTopic();
    this.resultTopic = singleTestComponent.resultTopic();
  }

  @Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> parameters() {
    return TestComponent.builder().embeddedKafka(KAFKA_TEST_RESOURCE).build().parameters();
  }

  @AfterParam
  public static void afterParam(String description, SingleTestComponent singleTestComponent) {
    singleTestComponent.testDriver().close();
  }

  @Before
  public void before() {
    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);
  }

  private void pipeState(SensorState sensorState) {
    inputTopic.pipeInput(sensorState.getId(), sensorState);
  }

  @Test
  public void testTopology() {
    var sensorId = "sensor-test1";

    var start = Instant.ofEpochSecond(443634300L);

    var reading1 = Reading.builder().time(start).position(Position.OFF).build();

    var sensorReading1 = SensorState.builder().id(sensorId).reading(reading1).build();

    pipeState(sensorReading1);
    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);

    var next = start.plusSeconds(30);
    var reading2 = Reading.builder().time(next).position(Position.ON).build();
    var newState = SensorState.builder().id(sensorId).reading(reading2).build();

    pipeState(newState);

    var result2 = resultTopic.readKeyValue();

    assertThat(result2.key).isEqualTo(sensorId);
    assertThat(result2.value).isNotNull();
    assertThat(result2.value.getReading()).isEqualTo(sensorReading1.getReading());
    assertThat(result2.value.getDuration()).isEqualTo(Duration.ofSeconds(30));

    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);
  }

  @Test
  public void testRepeated() {
    var sensorId = "sensor-test2";

    var start = Instant.ofEpochSecond(443634300L);

    var initialState =
        SensorState.builder()
            .id(sensorId)
            .reading(Reading.builder().time(start).position(Position.OFF).build())
            .build();

    pipeState(initialState);
    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);

    var next = start.plusSeconds(30);
    var newState =
        SensorState.builder()
            .id(sensorId)
            .reading(Reading.builder().time(next).position(Position.OFF).build())
            .build();

    pipeState(newState);

    var result2 = resultTopic.readKeyValue();

    assertThat(result2.key).isEqualTo(sensorId);
    assertThat(result2.value).isNotNull();
    assertThat(result2.value.getReading()).isEqualTo(initialState.getReading());
    assertThat(result2.value.getDuration()).isEqualTo(Duration.ofSeconds(30));

    var next2 = next.plusSeconds(30);
    var newState2 =
        SensorState.builder()
            .id(sensorId)
            .reading(Reading.builder().time(next2).position(Position.ON).build())
            .build();

    pipeState(newState2);

    var result3 = resultTopic.readKeyValue();

    assertThat(result3.key).isEqualTo(sensorId);
    assertThat(result3.value).isNotNull();
    assertThat(result3.value.getReading()).isEqualTo(initialState.getReading());
    assertThat(result3.value.getDuration()).isEqualTo(Duration.ofSeconds(60));

    var next3 = next2.plusSeconds(15);
    var newState3 =
        SensorState.builder()
            .id(sensorId)
            .reading(Reading.builder().time(next3).position(Position.OFF).build())
            .build();

    pipeState(newState3);

    var result4 = resultTopic.readKeyValue();

    assertThat(result4.key).isEqualTo(sensorId);
    assertThat(result4.value).isNotNull();
    assertThat(result4.value.getReading()).isEqualTo(newState2.getReading());
    assertThat(result4.value.getDuration()).isEqualTo(Duration.ofSeconds(15));

    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);
  }

  @Test
  @SuppressWarnings("nullness:argument") // TestInputTopic is not annotated
  public void testTombstone() {
    var sensorId = "sensor-test3";
    var start = Instant.ofEpochSecond(443634300L);

    var initialState =
        SensorState.builder()
            .id(sensorId)
            .reading(Reading.builder().time(start).position(Position.OFF).build())
            .build();

    pipeState(initialState);
    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);

    inputTopic.pipeInput(sensorId, null);
    var result2 = resultTopic.readKeyValue();
    assertThat(result2.key).isEqualTo(sensorId);
    assertThat(result2.value).isNull();

    var next = start.minusSeconds(30);
    var newState =
        SensorState.builder()
            .id(sensorId)
            .reading(Reading.builder().time(next).position(Position.OFF).build())
            .build();

    pipeState(newState);
    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);
  }

  @Test
  public void testBackwards() {
    var sensorId = "sensor-test4";
    var start = Instant.ofEpochSecond(443634300L);

    var initialState =
        SensorState.builder()
            .id(sensorId)
            .reading(Reading.builder().time(start).position(Position.OFF).build())
            .build();

    pipeState(initialState);
    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);

    var next = start.minusSeconds(30);
    var newState =
        SensorState.builder()
            .id(sensorId)
            .reading(Reading.builder().time(next).position(Position.OFF).build())
            .build();

    var excpetion = assertThrows(StreamsException.class, () -> pipeState(newState));
    // This is an implementation detail of our model and should probably not be tested here.
    assertThat(excpetion).hasCauseThat().isInstanceOf(IllegalStateException.class);

    assertThat(resultTopic.getQueueSize()).isEqualTo(0L);
  }
}
