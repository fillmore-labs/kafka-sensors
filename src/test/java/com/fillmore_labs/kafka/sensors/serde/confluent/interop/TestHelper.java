package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.time.Duration;
import java.time.Instant;

/* package */ final class TestHelper {
  /* package */ static final String KAFKA_TOPIC = "topic";

  private TestHelper() {}

  /* package */ static StateDuration standardStateDuration() {
    var time = Instant.ofEpochSecond(443634300L, 1L);

    return StateDuration.builder()
        .id("7331")
        .event(Event.builder().time(time).position(Event.Position.ON).build())
        .duration(Duration.ofSeconds(15, 999_999_999L))
        .build();
  }
}
