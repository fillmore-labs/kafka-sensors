package com.fillmore_labs.kafka.sensors.logic;

import static com.google.common.truth.Truth8.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.logic.ProcessorTestHelper.Advancement;
import com.fillmore_labs.kafka.sensors.model.Reading.Position;
import java.time.Duration;
import org.junit.Rule;
import org.junit.Test;

public final class CalculatorTest {
  @Rule public final CalculatorResource calculator = new CalculatorResource();

  @Test
  public void simple() {
    var state1 = ProcessorTestHelper.initial(Position.OFF);

    var advancement = Advancement.ofSecondsTo(30, Position.ON);
    var state2 = ProcessorTestHelper.advance(state1, advancement);

    var result1 = calculator.compute(state1).orElse(null);
    var result2 = calculator.compute(state2).orElse(null);

    ProcessorTestHelper.assertStateDuration(result1, null, Duration.ZERO);
    ProcessorTestHelper.assertStateDuration(result2, state1, advancement.duration());
  }

  @Test
  public void repeated() {
    var state1 = ProcessorTestHelper.initial(Position.OFF);

    var advancement1 = Advancement.ofSecondsTo(30, Position.OFF);
    var state2 = ProcessorTestHelper.advance(state1, advancement1);

    var advancement2 = Advancement.ofSecondsTo(30, Position.ON);
    var state3 = ProcessorTestHelper.advance(state2, advancement2);

    var advancement3 = Advancement.ofSecondsTo(15, Position.OFF);
    var state4 = ProcessorTestHelper.advance(state3, advancement3);

    var result1 = calculator.compute(state1).orElse(null);
    var result2 = calculator.compute(state2).orElse(null);
    var result3 = calculator.compute(state3).orElse(null);
    var result4 = calculator.compute(state4).orElse(null);

    ProcessorTestHelper.assertStateDuration(result1, null, Duration.ZERO);
    ProcessorTestHelper.assertStateDuration(result2, state1, advancement1.duration());
    ProcessorTestHelper.assertStateDuration(
        result3, state1, advancement1.duration().plus(advancement2.duration()));
    ProcessorTestHelper.assertStateDuration(result4, state3, advancement3.duration());
  }

  @Test
  public void outOfOrder() {
    var state1 = ProcessorTestHelper.initial(Position.OFF);

    var advancement1 = Advancement.ofSecondsTo(-10, Position.ON);
    var state2 = ProcessorTestHelper.advance(state1, advancement1);

    calculator.compute(state1);
    var calc = calculator; // effectively final (JLS §4.12.4)
    assertThrows(IllegalStateException.class, () -> calc.compute(state2));
  }

  @Test
  public void nullHandling() {
    var state1 = ProcessorTestHelper.initial(Position.OFF);

    var advancement1 = Advancement.ofSecondsTo(-10, Position.ON);
    var state2 = ProcessorTestHelper.advance(state1, advancement1);

    calculator.compute(state1);
    calculator.delete();
    var result = calculator.compute(state2);

    assertThat(result).isEmpty();
  }
}
