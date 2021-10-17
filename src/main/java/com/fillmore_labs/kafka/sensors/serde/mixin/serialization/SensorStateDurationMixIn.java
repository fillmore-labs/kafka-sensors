package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fillmore_labs.kafka.sensors.model.ImmutableSensorStateDuration;
import com.fillmore_labs.kafka.sensors.model.SensorState;

@JsonDeserialize(builder = ImmutableSensorStateDuration.Builder.class)
public abstract class SensorStateDurationMixIn {
  private SensorStateDurationMixIn() {}

  @JsonUnwrapped
  public abstract SensorState getEvent();

  @JsonPOJOBuilder(withPrefix = "")
  public abstract static class BuilderMixIn {
    private BuilderMixIn() {}

    @JsonUnwrapped
    public abstract ImmutableSensorStateDuration.Builder event(SensorState sensorState);
  }
}
