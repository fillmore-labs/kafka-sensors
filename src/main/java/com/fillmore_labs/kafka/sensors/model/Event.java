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
public abstract class Event implements WithEvent {
  /* package */ Event() {}

  public static ImmutableEvent.Builder builder() {
    return ImmutableEvent.builder();
  }

  public abstract Instant getTime();

  public abstract Position getPosition();

  public enum Position {
    OFF,
    ON
  }
}
