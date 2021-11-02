package com.fillmore_labs.kafka.sensors.model;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.time.Duration;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class ReadingDuration implements WithReadingDuration {
  /* package */ ReadingDuration() {}

  public static ImmutableReadingDuration.Builder builder() {
    return ImmutableReadingDuration.builder();
  }

  public abstract Reading getReading();

  public abstract Duration getDuration();

  @Value.Check
  /* package */ final void check() {
    var duration = getDuration();
    Preconditions.checkState(!duration.isNegative(), "Duration %s is negative", duration);
  }
}
