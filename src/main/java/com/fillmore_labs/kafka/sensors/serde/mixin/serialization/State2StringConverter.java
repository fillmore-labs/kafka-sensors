package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.fillmore_labs.kafka.sensors.model.SensorState;

public final class State2StringConverter extends StdConverter<SensorState.State, String> {
  @Override
  public String convert(SensorState.State value) {
    // Checkstyle ignore MissingSwitchDefault
    switch (value) {
      case OFF:
        return "off";
      case ON:
        return "on";
    }
    throw new IllegalStateException("Unexpected state: " + value);
  }
}
