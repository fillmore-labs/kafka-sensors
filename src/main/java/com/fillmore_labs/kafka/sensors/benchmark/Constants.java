package com.fillmore_labs.kafka.sensors.benchmark;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.time.Duration;
import java.time.Instant;

/* package */ final class Constants {
  public static final String TOPIC = "topic";

  private Constants() {}

  public static SensorStateDuration createData() {
    var instant = Instant.ofEpochSecond(443_634_300L);

    var event = SensorState.builder().id("7331").time(instant).state(State.ON).build();

    return SensorStateDuration.builder().event(event).duration(Duration.ofSeconds(15)).build();
  }
}
