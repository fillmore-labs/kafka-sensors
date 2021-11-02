package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fillmore_labs.kafka.sensors.model.ImmutableSensorState;
import com.fillmore_labs.kafka.sensors.model.Reading;

@JsonDeserialize(builder = ImmutableSensorState.Builder.class)
interface SensorStateMixIn {
  @JsonUnwrapped
  Reading getReading();

  @JsonPOJOBuilder(withPrefix = "")
  interface BuilderMixIn {
    @JsonUnwrapped
    ImmutableSensorState.Builder reading(Reading reading);
  }
}
