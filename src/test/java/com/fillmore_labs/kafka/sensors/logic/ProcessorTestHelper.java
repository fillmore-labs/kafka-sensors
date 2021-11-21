package com.fillmore_labs.kafka.sensors.logic;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.Reading.Position;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import java.time.Duration;
import java.time.Instant;
import org.checkerframework.checker.nullness.qual.Nullable;

/* package */ final class ProcessorTestHelper {
  private ProcessorTestHelper() {}

  /* package */ static Reading initial(Position position) {
    var instant = Instant.ofEpochSecond(443_634_300L);
    return Reading.builder().time(instant).position(position).build();
  }

  /* package */ static Reading advance(Reading old, Advancement advancement) {
    var newTime = old.getTime().plus(advancement.duration());
    return Reading.builder().time(newTime).position(advancement.position()).build();
  }

  /**
   * Test for correct transformation.
   *
   * @param result Transformation processor result
   * @param duration Expected duration the old position lasted
   * @throws AssertionError When the position is not transformed correctly
   */
  /* package */ static void assertStateDuration(
      @Nullable ReadingDuration result, @Nullable Reading expectedState, Duration duration) {
    var resultState = result == null ? null : result.getReading();
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
