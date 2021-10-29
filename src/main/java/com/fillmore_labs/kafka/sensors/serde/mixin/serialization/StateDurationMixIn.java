package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.ImmutableStateDuration;

@JsonDeserialize(builder = ImmutableStateDuration.Builder.class)
interface StateDurationMixIn {
  @JsonUnwrapped
  Event getEvent();

  @JsonPOJOBuilder(withPrefix = "")
  interface BuilderMixIn {
    @JsonUnwrapped
    ImmutableStateDuration.Builder event(Event event);
  }
}
