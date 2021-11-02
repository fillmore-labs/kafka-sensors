package com.fillmore_labs.kafka.sensors.model;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.time.Duration;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class StateDuration implements WithStateDuration {
  /* package */ StateDuration() {}

  public static ImmutableStateDuration.Builder builder() {
    return ImmutableStateDuration.builder();
  }

  public abstract String getId();

  public abstract Reading getReading();

  public abstract Duration getDuration();

  @Value.Check
  /* package */ final void check() {
    var duration = getDuration();
    Preconditions.checkState(
        !duration.isNegative(), "Duration %s of sensor %s is negative", duration, getId());
  }
}
