package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import com.google.errorprone.annotations.Immutable;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class SensorStateGson {
  /* package */ SensorStateGson() {}

  public static ImmutableSensorStateGson.Builder builder() {
    return ImmutableSensorStateGson.builder();
  }

  public abstract String getId();

  public abstract BigDecimal getTime();

  public abstract State getState();

  public enum State {
    @SerializedName("off")
    OFF,
    @SerializedName("on")
    ON
  }
}
