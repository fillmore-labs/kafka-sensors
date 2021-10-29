package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.Instant;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableEventJson.Builder.class)
public interface EventJson {
  static ImmutableEventJson.Builder builder() {
    return ImmutableEventJson.builder();
  }

  Instant getTime();

  Position getPosition();

  enum Position {
    @JsonProperty("off")
    OFF,
    @JsonProperty("on")
    ON
  }
}
