package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import com.google.errorprone.annotations.Immutable;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Immutable
@Value.Style(passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class SensorStateDurationGson {
  /* package */ SensorStateDurationGson() {}

  public static ImmutableSensorStateDurationGson.Builder builder() {
    return ImmutableSensorStateDurationGson.builder();
  }

  public abstract String getId();

  public abstract BigDecimal getTime();

  public abstract SensorStateGson.State getState();

  public abstract BigDecimal getDuration();
}
