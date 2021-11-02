package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.time.Duration;
import java.time.Instant;

/* package */ final class TestHelper {
  private TestHelper() {}

  /* package */ static Reading standardReading() {
    var instant = Instant.ofEpochSecond(443634300L, 1L);

    return Reading.builder().time(instant).position(Reading.Position.ON).build();
  }

  /* package */ static SensorState standardSensorState() {
    return SensorState.builder().id("7331").reading(standardReading()).build();
  }

  /* package */ static StateDuration standardStateDuration() {
    return StateDuration.builder()
        .id("7331")
        .reading(standardReading())
        .duration(Duration.ofSeconds(15, 999_999_999L))
        .build();
  }
}
