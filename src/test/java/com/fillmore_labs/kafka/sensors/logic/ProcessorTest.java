package com.fillmore_labs.kafka.sensors.logic;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.logic.ProcessorTestHelper.Advancement;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import java.time.Duration;
import org.junit.Rule;
import org.junit.Test;

public final class ProcessorTest {
  @Rule public final ProcessorResource processor = new ProcessorResource();

  @Test
  public void testSimple() {
    var initialState = ProcessorTestHelper.initial(SensorState.State.OFF);
    var advancement = new Advancement(Duration.ofSeconds(30), SensorState.State.ON);

    var newState = ProcessorTestHelper.advance(initialState, advancement);

    var result1 = processor.transform(initialState);
    var result2 = processor.transform(newState);

    ProcessorTestHelper.assertStateDuration(result1, null, Duration.ZERO);
    ProcessorTestHelper.assertStateDuration(result2, initialState, advancement.duration);
  }

  @Test
  public void testRepeated() {
    var initialState = ProcessorTestHelper.initial(SensorState.State.OFF);
    var advancement1 = new Advancement(Duration.ofSeconds(30), SensorState.State.OFF);
    var advancement2 = new Advancement(Duration.ofSeconds(30), SensorState.State.ON);
    var advancement3 = new Advancement(Duration.ofSeconds(15), SensorState.State.OFF);

    var newState1 = ProcessorTestHelper.advance(initialState, advancement1);
    var newState2 = ProcessorTestHelper.advance(newState1, advancement2);
    var newState3 = ProcessorTestHelper.advance(newState2, advancement3);

    var result1 = processor.transform(initialState);
    var result2 = processor.transform(newState1);
    var result3 = processor.transform(newState2);
    var result4 = processor.transform(newState3);

    var duration1 = Duration.ZERO;
    var duration2 = advancement1.duration;
    var duration3 = duration2.plus(advancement2.duration);
    var duration4 = advancement3.duration;

    ProcessorTestHelper.assertStateDuration(result1, null, duration1);
    ProcessorTestHelper.assertStateDuration(result2, initialState, duration2);
    ProcessorTestHelper.assertStateDuration(result3, initialState, duration3);
    ProcessorTestHelper.assertStateDuration(result4, newState2, duration4);
  }

  @Test
  public void outOfOrder() {
    var initialState = ProcessorTestHelper.initial(SensorState.State.OFF);
    var advancement1 = new Advancement(Duration.ofSeconds(-10), SensorState.State.ON);

    var newState1 = ProcessorTestHelper.advance(initialState, advancement1);

    processor.transform(initialState);
    var calc = processor; // effectively final (JLS §4.12.4)
    assertThrows(IllegalStateException.class, () -> calc.transform(newState1));
  }

  @Test
  public void nullHandling() {
    var initialState = ProcessorTestHelper.initial(SensorState.State.OFF);
    var advancement1 = new Advancement(Duration.ofSeconds(-10), SensorState.State.ON);

    var newState1 = ProcessorTestHelper.advance(initialState, advancement1);

    processor.transform(initialState);
    var result1 = processor.tombstone(ProcessorTestHelper.SENSOR_ID);
    var result2 = processor.transform(newState1);

    assertThat(result1).isNull();
    assertThat(result2).isNull();
  }
}
