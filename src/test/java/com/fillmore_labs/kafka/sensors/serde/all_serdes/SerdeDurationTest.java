package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.time.Duration;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class SerdeDurationTest extends SerdeTestBase<SensorStateDuration> {
  public SerdeDurationTest(String description, SingleTestComponent singleTestComponent) {
    super(singleTestComponent::injectMembersDuration, standardSensorStateDuration());
  }

  @Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> parameters() {
    return TestComponent.create().parameters();
  }

  /* package */ static SensorStateDuration standardSensorStateDuration() {
    var event = SerdeTest.standardSensorState();

    return SensorStateDuration.builder()
        .event(event)
        .duration(Duration.ofSeconds(15, 999_999_000L))
        .build();
  }
}
