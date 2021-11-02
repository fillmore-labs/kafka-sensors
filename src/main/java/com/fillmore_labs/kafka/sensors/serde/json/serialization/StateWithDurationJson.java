package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.Duration;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableStateWithDurationJson.Builder.class)
public interface StateWithDurationJson {
  static ImmutableStateWithDurationJson.Builder builder() {
    return ImmutableStateWithDurationJson.builder();
  }

  String getId();

  @JsonUnwrapped
  ReadingJson getReading();

  Duration getDuration();
}
