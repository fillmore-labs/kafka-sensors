package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.errorprone.annotations.Immutable;
import java.time.Duration;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
@JsonDeserialize(builder = ImmutableSensorStateDurationJson.Builder.class)
public abstract class SensorStateDurationJson {
  /* package */ SensorStateDurationJson() {}

  public static ImmutableSensorStateDurationJson.Builder builder() {
    return ImmutableSensorStateDurationJson.builder();
  }

  @JsonUnwrapped
  public abstract SensorStateJson getEvent();

  public abstract Duration getDuration();
}
