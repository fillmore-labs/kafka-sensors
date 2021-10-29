package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.fillmore_labs.kafka.sensors.model.Event;

public final class String2PositionConverter extends StdConverter<String, Event.Position> {
  @Override
  public Event.Position convert(String value) {
    switch (value) {
      case "off":
        return Event.Position.OFF;

      case "on":
        return Event.Position.ON;

      default:
        throw new IllegalArgumentException("Expecting [off,on], got " + value);
    }
  }
}
