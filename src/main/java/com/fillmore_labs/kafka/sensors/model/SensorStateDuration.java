package com.fillmore_labs.kafka.sensors.model;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.time.Duration;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class SensorStateDuration implements WithSensorStateDuration {
  /* package */ SensorStateDuration() {}

  public static ImmutableSensorStateDuration.Builder builder() {
    return ImmutableSensorStateDuration.builder();
  }

  public abstract SensorState getEvent();

  public abstract Duration getDuration();

  @Value.Check
  /* package */ void check() {
    var duration = getDuration();
    Preconditions.checkState(
        !duration.isNegative(), "Duration %s of event %s is negative", duration, getEvent());
  }
}
