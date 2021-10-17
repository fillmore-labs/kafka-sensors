package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class SensorStateIon {
  /* package */ SensorStateIon() {}

  public static ImmutableSensorStateIon.Builder builder() {
    return ImmutableSensorStateIon.builder();
  }

  public abstract String getId();

  public abstract Instant getTime();

  public abstract State getState();

  public enum State {
    OFF,
    ON
  }
}
