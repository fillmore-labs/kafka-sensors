package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.ImmutableEvent;

@JsonDeserialize(builder = ImmutableEvent.Builder.class)
interface EventMixIn {
  Event.Position getPosition();

  @JsonPOJOBuilder(withPrefix = "")
  interface BuilderMixIn {}

  @JsonSerialize(converter = Position2StringConverter.class)
  @JsonDeserialize(converter = String2PositionConverter.class)
  interface PositionMixIn {}
}
