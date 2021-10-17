package com.fillmore_labs.kafka.sensors.logic;

import static com.fillmore_labs.kafka.sensors.logic.CalculatorTestHelper.assertStateDuration;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.logic.CalculatorTestHelper.Advancement;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import java.time.Duration;
import org.junit.Rule;
import org.junit.Test;

public final class CalculatorTest {
  @Rule public final CalculatorResource calculator = new CalculatorResource();

  @Test
  public void testSimple() {
    var initialState = CalculatorTestHelper.initial(State.OFF);
    var advancement = new Advancement(Duration.ofSeconds(30), State.ON);

    var newState = CalculatorTestHelper.advance(initialState, advancement);

    var result1 = calculator.transform(initialState);
    var result2 = calculator.transform(newState);

    assertStateDuration(result1, null, Duration.ZERO);
    assertStateDuration(result2, initialState, advancement.duration);
  }

  @Test
  public void testRepeated() {
    var initialState = CalculatorTestHelper.initial(State.OFF);
    var advancement1 = new Advancement(Duration.ofSeconds(30), State.OFF);
    var advancement2 = new Advancement(Duration.ofSeconds(30), State.ON);
    var advancement3 = new Advancement(Duration.ofSeconds(15), State.OFF);

    var newState1 = CalculatorTestHelper.advance(initialState, advancement1);
    var newState2 = CalculatorTestHelper.advance(newState1, advancement2);
    var newState3 = CalculatorTestHelper.advance(newState2, advancement3);

    var result1 = calculator.transform(initialState);
    var result2 = calculator.transform(newState1);
    var result3 = calculator.transform(newState2);
    var result4 = calculator.transform(newState3);

    var duration1 = Duration.ZERO;
    var duration2 = advancement1.duration;
    var duration3 = duration2.plus(advancement2.duration);
    var duration4 = advancement3.duration;

    assertStateDuration(result1, null, duration1);
    assertStateDuration(result2, initialState, duration2);
    assertStateDuration(result3, initialState, duration3);
    assertStateDuration(result4, newState2, duration4);
  }

  @Test
  public void outOfOrder() {
    var initialState = CalculatorTestHelper.initial(State.OFF);
    var advancement1 = new Advancement(Duration.ofSeconds(-10), State.ON);

    var newState1 = CalculatorTestHelper.advance(initialState, advancement1);

    calculator.transform(initialState);
    var calc = calculator; // effectively final (JLS §4.12.4)
    assertThrows(IllegalStateException.class, () -> calc.transform(newState1));
  }

  @Test
  public void nullHandling() {
    var result = calculator.transform(null);
    assertThat(result).isNull();
  }
}
