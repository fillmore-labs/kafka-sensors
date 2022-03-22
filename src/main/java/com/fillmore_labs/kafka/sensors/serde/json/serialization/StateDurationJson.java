package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.Duration;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableStateDurationJson.Builder.class)
public interface StateDurationJson {
  static ImmutableStateDurationJson.Builder builder() {
    return ImmutableStateDurationJson.builder();
  }

  String getId();

  @JsonUnwrapped
  ReadingJson getReading();

  Duration getDuration();
}
