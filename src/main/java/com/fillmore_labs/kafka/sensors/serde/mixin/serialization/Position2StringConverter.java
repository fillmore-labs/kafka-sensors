package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.fillmore_labs.kafka.sensors.model.Event;

public final class Position2StringConverter extends StdConverter<Event.Position, String> {
  @Override
  public String convert(Event.Position value) {
    // Checkstyle ignore MissingSwitchDefault
    switch (value) {
      case OFF:
        return "off";
      case ON:
        return "on";
    }
    throw new IllegalStateException("Unexpected position: " + value);
  }
}
