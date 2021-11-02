package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import com.fillmore_labs.kafka.sensors.serde.gson.serialization.ReadingGson.Position;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Value.Immutable
public interface SensorStateGson {
  static ImmutableSensorStateGson.Builder builder() {
    return ImmutableSensorStateGson.builder();
  }

  String getId();

  BigDecimal getTime();

  Position getPosition();
}
