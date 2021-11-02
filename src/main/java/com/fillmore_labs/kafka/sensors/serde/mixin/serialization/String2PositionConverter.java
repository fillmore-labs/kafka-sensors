package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.fillmore_labs.kafka.sensors.model.Reading;

public final class String2PositionConverter extends StdConverter<String, Reading.Position> {
  @Override
  public Reading.Position convert(String value) {
    switch (value) {
      case "off":
        return Reading.Position.OFF;

      case "on":
        return Reading.Position.ON;

      default:
        throw new IllegalArgumentException("Expecting [off,on], got " + value);
    }
  }
}
