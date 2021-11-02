package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import com.fillmore_labs.kafka.sensors.serde.gson.serialization.ReadingGson.Position;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Value.Immutable
public interface StateDurationGson {
  static ImmutableStateDurationGson.Builder builder() {
    return ImmutableStateDurationGson.builder();
  }

  String getId();

  BigDecimal getTime();

  Position getPosition();

  BigDecimal getDuration();
}
