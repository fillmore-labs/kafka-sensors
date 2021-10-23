package com.fillmore_labs.kafka.sensors.serde.json;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import java.time.Instant;

public final class SerdeTest extends SerdeTestBase<SensorState> {
  public SerdeTest() {
    super(standardSensorState());
    TestComponent.create().inject(this);
  }

  /* package */ static SensorState standardSensorState() {
    var instant = Instant.ofEpochSecond(443634300L, 1_000L);

    return SensorState.builder().id("7331").time(instant).state(State.ON).build();
  }
}
