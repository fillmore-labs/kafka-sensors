package com.fillmore_labs.kafka.sensors.serde.proto.serialization;

import com.fillmore_labs.kafka.sensors.proto.v1.Reading;
import com.fillmore_labs.kafka.sensors.proto.v1.StateDuration;
import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;

public final class TestHelper {
  private TestHelper() {}

  /* package */ static com.fillmore_labs.kafka.sensors.test.proto.v1.StateDuration
      createTestStateDuration() {
    var reading = createReading();

    return com.fillmore_labs.kafka.sensors.test.proto.v1.StateDuration.newBuilder()
        .setId("3771")
        .setReading(reading)
        .setComment("New Field")
        .setDuration(Duration.newBuilder().setSeconds(15L))
        .build();
  }

  /* package */ static StateDuration createStateDuration() {
    var reading = createReading();

    return StateDuration.newBuilder()
        .setId("3771")
        .setReading(reading)
        .setDuration(Duration.newBuilder().setSeconds(15L))
        .build();
  }

  private static Reading createReading() {
    return Reading.newBuilder()
        .setTime(Timestamp.newBuilder().setSeconds(443_634_300L))
        .setPosition(Reading.Position.POSITION_ON)
        .build();
  }
}
