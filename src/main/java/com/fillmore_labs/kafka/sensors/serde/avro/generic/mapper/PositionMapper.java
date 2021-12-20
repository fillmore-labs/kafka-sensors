package com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.PositionSchema;
import com.google.errorprone.annotations.Immutable;
import javax.inject.Inject;

@Immutable
public final class PositionMapper {
  @Inject
  /* package */ PositionMapper() {}

  @SuppressWarnings("UnnecessaryParentheses")
  public Object map(Reading.Position model) {
    return switch (model) {
      case OFF -> PositionSchema.ENUM_OFF;
      case ON -> PositionSchema.ENUM_ON;
    };
  }

  @SuppressWarnings("UnnecessaryParentheses")
  public Reading.Position unmap(Object data) {
    return switch (data.toString()) {
      case PositionSchema.POSITION_OFF -> Reading.Position.OFF;
      case PositionSchema.POSITION_ON -> Reading.Position.ON;
      default -> throw new IllegalArgumentException("Unexpected Enum value: " + data);
    };
  }
}
