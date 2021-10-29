package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.time.Duration;
import java.time.Instant;

/* package */ final class TestHelper {
  private TestHelper() {}

  /* package */ static Event standardEvent() {
    var instant = Instant.ofEpochSecond(443634300L, 1L);

    return Event.builder().time(instant).position(Event.Position.ON).build();
  }

  /* package */ static SensorState standardSensorState() {
    return SensorState.builder().id("7331").event(standardEvent()).build();
  }

  /* package */ static StateDuration standardStateDuration() {
    return StateDuration.builder()
        .id("7331")
        .event(standardEvent())
        .duration(Duration.ofSeconds(15, 999_999_999L))
        .build();
  }
}
