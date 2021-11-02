package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import org.immutables.value.Value;

@Value.Immutable
public interface ReadingIon {
  static ImmutableReadingIon.Builder builder() {
    return ImmutableReadingIon.builder();
  }

  long getTime();

  Position getPosition();

  enum Position {
    OFF,
    ON
  }
}
