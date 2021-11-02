package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import org.immutables.value.Value;

@Value.Immutable
public interface SensorStateIon {
  static ImmutableSensorStateIon.Builder builder() {
    return ImmutableSensorStateIon.builder();
  }

  String getId();

  ReadingIon getReading();
}
