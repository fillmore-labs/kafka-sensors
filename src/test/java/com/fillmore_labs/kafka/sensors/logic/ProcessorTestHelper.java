package com.fillmore_labs.kafka.sensors.logic;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.Event.Position;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.time.Duration;
import java.time.Instant;
import org.checkerframework.checker.nullness.qual.Nullable;

/* package */ final class ProcessorTestHelper {
  /* package */ static final String SENSOR_ID = "7331";

  private ProcessorTestHelper() {}

  /* package */ static SensorState initial(Position position) {
    var instant = Instant.ofEpochSecond(443634300L);
    return SensorState.builder()
        .id(SENSOR_ID)
        .event(Event.builder().time(instant).position(position).build())
        .build();
  }

  /* package */ static SensorState advance(SensorState old, Advancement advancement) {
    var newTime = old.getEvent().getTime().plus(advancement.duration());
    var newEvent = Event.builder().time(newTime).position(advancement.position()).build();
    return old.withEvent(newEvent);
  }

  /**
   * Test for correct transformation.
   *
   * @param result Transformation processor result
   * @param duration Expected duration the old position lasted
   * @throws AssertionError When the position is not transformed correctly
   */
  /* package */ static void assertStateDuration(
      @Nullable StateDuration result, @Nullable Event expectedState, Duration duration) {
    var resultState = result == null ? null : result.getEvent();
    assertThat(resultState).isEqualTo(expectedState);

    var resultDuration = result == null ? Duration.ZERO : result.getDuration();
    assertThat(resultDuration).isEqualTo(duration);
  }

  @SuppressWarnings("UnusedVariable")
  /* package */ record Advancement(Duration duration, Position position) {
    public static Advancement ofSecondsTo(long seconds, Position to) {
      return new Advancement(Duration.ofSeconds(seconds), to);
    }
  }
}
