package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import com.google.errorprone.annotations.Immutable;
import java.time.Duration;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class SensorStateDurationIon {
  /* package */ SensorStateDurationIon() {}

  public static ImmutableSensorStateDurationIon.Builder builder() {
    return ImmutableSensorStateDurationIon.builder();
  }

  public abstract SensorStateIon getEvent();

  public abstract Duration getDuration();
}
