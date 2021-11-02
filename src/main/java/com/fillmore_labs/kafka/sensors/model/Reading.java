package com.fillmore_labs.kafka.sensors.model;

import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import org.immutables.value.Value;

@Immutable
@Value.Style(
    passAnnotations = {Immutable.class},
    of = "new",
    allParameters = true)
@Value.Immutable
public abstract class Reading implements WithReading {
  /* package */ Reading() {}

  public static ImmutableReading.Builder builder() {
    return ImmutableReading.builder();
  }

  public abstract Instant getTime();

  public abstract Position getPosition();

  public enum Position {
    OFF,
    ON
  }
}
