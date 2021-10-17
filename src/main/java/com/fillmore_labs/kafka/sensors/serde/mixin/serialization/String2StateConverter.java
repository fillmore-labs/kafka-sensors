package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.fillmore_labs.kafka.sensors.model.SensorState;

public final class String2StateConverter extends StdConverter<String, SensorState.State> {
  @Override
  public SensorState.State convert(String value) {
    switch (value) {
      case "off":
        return SensorState.State.OFF;

      case "on":
        return SensorState.State.ON;

      default:
        throw new IllegalArgumentException("Expecting [off,on], got " + value);
    }
  }
}
