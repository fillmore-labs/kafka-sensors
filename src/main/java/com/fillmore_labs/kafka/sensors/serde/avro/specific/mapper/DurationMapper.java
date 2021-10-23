package com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationMicroHelper;
import com.google.errorprone.annotations.Immutable;
import java.time.Duration;

@Immutable
public final class DurationMapper {
  private DurationMapper() {}

  public static Duration milcros2Duration(long micros) {
    return DurationMicroHelper.micros2Duration(micros);
  }

  public static long duration2Micros(Duration duration) {
    return DurationMicroHelper.duration2Micros(duration);
  }
}
