package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Value.Immutable
public interface EventGson {
  static ImmutableEventGson.Builder builder() {
    return ImmutableEventGson.builder();
  }

  BigDecimal getTime();

  Position getPosition();

  enum Position {
    @SerializedName("off")
    OFF,
    @SerializedName("on")
    ON
  }
}
