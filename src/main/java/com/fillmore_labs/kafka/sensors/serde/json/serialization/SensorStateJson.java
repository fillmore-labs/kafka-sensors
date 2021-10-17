package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
@JsonDeserialize(builder = ImmutableSensorStateJson.Builder.class)
public abstract class SensorStateJson {
  /* package */ SensorStateJson() {}

  public static ImmutableSensorStateJson.Builder builder() {
    return ImmutableSensorStateJson.builder();
  }

  public abstract String getId();

  public abstract Instant getTime();

  public abstract State getState();

  public enum State {
    @JsonProperty("off")
    OFF,
    @JsonProperty("on")
    ON
  }
}
