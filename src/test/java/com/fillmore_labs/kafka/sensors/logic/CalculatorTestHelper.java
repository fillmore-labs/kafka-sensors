package com.fillmore_labs.kafka.sensors.logic;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.time.Duration;
import java.time.Instant;
import org.checkerframework.checker.nullness.qual.Nullable;

/* package */ final class CalculatorTestHelper {
  private static final String SENSOR_ID = "7331";

  private CalculatorTestHelper() {}

  /* package */ static SensorState initial(State state) {
    var instant = Instant.ofEpochSecond(443634300L);
    return SensorState.builder().id(SENSOR_ID).time(instant).state(state).build();
  }

  /* package */ static SensorState advance(SensorState old, Advancement advancement) {
    return SensorState.builder()
        .id(old.getId())
        .time(old.getTime().plus(advancement.duration))
        .state(advancement.state)
        .build();
  }

  /**
   * Test for correct transformation.
   *
   * @param result Transformation processor result
   * @param duration Expected duration the old state lasted
   * @throws AssertionError When the state is not transformed correctly
   */
  /* package */ static void assertStateDuration(
      @Nullable SensorStateDuration result,
      @Nullable SensorState expectedState,
      Duration duration) {
    var resultState = result == null ? null : result.getEvent();
    assertThat(resultState).isEqualTo(expectedState);

    var resultDuration = result == null ? Duration.ZERO : result.getDuration();
    assertThat(resultDuration).isEqualTo(duration);
  }

  /* package */ static final class Advancement {
    public final Duration duration;
    public final State state;

    /* package */ Advancement(Duration duration, State state) {
      this.duration = duration;
      this.state = state;
    }
  }
}
