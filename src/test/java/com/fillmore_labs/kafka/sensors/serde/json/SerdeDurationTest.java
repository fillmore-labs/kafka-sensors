package com.fillmore_labs.kafka.sensors.serde.json;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.time.Duration;

public final class SerdeDurationTest extends SerdeTestBase<SensorStateDuration> {
  public SerdeDurationTest() {
    super(standardSensorStateDuration());
    TestComponent.create().inject(this);
  }

  /* package */ static SensorStateDuration standardSensorStateDuration() {
    var event = SerdeTest.standardSensorState();

    return SensorStateDuration.builder()
        .event(event)
        .duration(Duration.ofSeconds(15, 999_999_000L))
        .build();
  }
}
