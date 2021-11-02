package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fillmore_labs.kafka.sensors.model.ImmutableReading;
import com.fillmore_labs.kafka.sensors.model.Reading;

@JsonDeserialize(builder = ImmutableReading.Builder.class)
interface ReadingMixIn {
  Reading.Position getPosition();

  @JsonPOJOBuilder(withPrefix = "")
  interface BuilderMixIn {}

  @JsonSerialize(converter = Position2StringConverter.class)
  @JsonDeserialize(converter = String2PositionConverter.class)
  interface PositionMixIn {}
}
