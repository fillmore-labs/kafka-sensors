package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.Instant;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableReadingJson.Builder.class)
public interface ReadingJson {
  static ImmutableReadingJson.Builder builder() {
    return ImmutableReadingJson.builder();
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
