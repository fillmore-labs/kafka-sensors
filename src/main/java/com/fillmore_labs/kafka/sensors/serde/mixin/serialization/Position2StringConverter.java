package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.fillmore_labs.kafka.sensors.model.Reading;

public final class Position2StringConverter extends StdConverter<Reading.Position, String> {
  @Override
  @SuppressWarnings("UnnecessaryParentheses")
  public String convert(Reading.Position value) {
    return switch (value) {
      case OFF -> "off";
      case ON -> "on";
    };
  }
}
