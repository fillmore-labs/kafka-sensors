package com.fillmore_labs.kafka.sensors.serde.thrift.serialization;

import com.fillmore_labs.kafka.sensors.thrift.v1.Position;
import com.fillmore_labs.kafka.sensors.thrift.v1.Reading;
import com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration;
import java.time.Duration;
import java.time.Instant;

public final class TestHelper {
  private static final Instant INSTANT = Instant.ofEpochSecond(443_634_300L);
  private static final long NANOS_PER_MILLI = 1_000_000L;

  private TestHelper() {}

  /* package */ static com.fillmore_labs.kafka.sensors.test.thrift.v1.StateDuration
      createTestStateDuration() {
    var reading = createReading();

    var sensorState = new com.fillmore_labs.kafka.sensors.test.thrift.v1.StateDuration();
    sensorState.setId("3771");
    sensorState.setReading(reading);
    sensorState.setComment("New Field");
    sensorState.setDuration(Duration.ofSeconds(15).toNanos());
    return sensorState;
  }

  /* package */ static StateDuration createStateDuration() {
    var reading = createReading();

    var sensorState = new StateDuration();
    sensorState.setId("3771");
    sensorState.setReading(reading);
    sensorState.setDuration(Duration.ofSeconds(15).toNanos());
    return sensorState;
  }

  private static Reading createReading() {
    var reading = new Reading();
    reading.setPosition(Position.ON);
    reading.setTime(INSTANT.toEpochMilli() * NANOS_PER_MILLI + 1L);
    return reading;
  }
}
