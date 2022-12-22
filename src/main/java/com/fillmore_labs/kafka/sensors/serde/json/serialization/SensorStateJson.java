package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableSensorStateJson.Builder.class)
public interface SensorStateJson {
  String getId();

  @JsonUnwrapped
  ReadingJson getReading();
}
