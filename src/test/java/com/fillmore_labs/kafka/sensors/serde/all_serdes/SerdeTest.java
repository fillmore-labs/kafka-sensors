package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import java.time.Instant;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class SerdeTest extends SerdeTestBase<SensorState> {
  public SerdeTest(String description, SingleTestComponent singleTestComponent) {
    super(singleTestComponent::injectMembers, standardSensorState());
  }

  @Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> parameters() {
    return TestComponent.create().parameters();
  }

  /* package */ static SensorState standardSensorState() {
    var instant = Instant.ofEpochSecond(443634300L, 1_000L);

    return SensorState.builder().id("7331").time(instant).state(State.ON).build();
  }
}
