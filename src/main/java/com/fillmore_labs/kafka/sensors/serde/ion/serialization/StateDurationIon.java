package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import org.immutables.value.Value;

@Value.Immutable
public interface StateDurationIon {
  static ImmutableStateDurationIon.Builder builder() {
    return ImmutableStateDurationIon.builder();
  }

  String getId();

  ReadingIon getReading();

  long getDuration();
}
