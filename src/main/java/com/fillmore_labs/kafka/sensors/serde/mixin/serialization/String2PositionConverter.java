package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.fillmore_labs.kafka.sensors.model.Reading;

public final class String2PositionConverter extends StdConverter<String, Reading.Position> {
  @Override
  @SuppressWarnings({"nullness:return", "UnnecessaryParentheses"})
  public Reading.Position convert(String value) {
    return switch (value) {
      case "off" -> Reading.Position.OFF;
      case "on" -> Reading.Position.ON;
      default -> throw new IllegalArgumentException("Expecting [off,on], got " + value);
    };
  }
}
