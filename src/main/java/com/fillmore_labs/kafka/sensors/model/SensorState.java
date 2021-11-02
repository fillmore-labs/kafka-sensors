package com.fillmore_labs.kafka.sensors.model;

import com.google.errorprone.annotations.Immutable;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class SensorState implements WithSensorState {
  /* package */ SensorState() {}

  public static ImmutableSensorState.Builder builder() {
    return ImmutableSensorState.builder();
  }

  public abstract String getId();

  public abstract Reading getReading();
}
