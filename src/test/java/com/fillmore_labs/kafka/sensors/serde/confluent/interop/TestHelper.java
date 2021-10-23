package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.time.Duration;
import java.time.Instant;

/* package */ final class TestHelper {
  /* package */ static final String KAFKA_TOPIC = "topic";

  private TestHelper() {}

  /* package */ static SensorState standardSensorState() {
    var instant = Instant.ofEpochSecond(443634300L, 1_000L);

    return SensorState.builder().id("7331").time(instant).state(State.ON).build();
  }

  /* package */ static SensorStateDuration standardSensorStateDuration() {
    var event = standardSensorState();

    return SensorStateDuration.builder()
        .event(event)
        .duration(Duration.ofSeconds(15, 999_999_000L))
        .build();
  }
}
