package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fillmore_labs.kafka.sensors.model.ImmutableSensorState;

@JsonDeserialize(builder = ImmutableSensorState.Builder.class)
public abstract class SensorStateMixIn {
  private SensorStateMixIn() {}

  @JsonPOJOBuilder(withPrefix = "")
  public abstract static class BuilderMixIn {
    private BuilderMixIn() {}
  }

  @JsonSerialize(converter = State2StringConverter.class)
  @JsonDeserialize(converter = String2StateConverter.class)
  public abstract static class StateMixIn {}
}
