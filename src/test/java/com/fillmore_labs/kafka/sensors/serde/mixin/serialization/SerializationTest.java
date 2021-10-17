package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.json.JsonTestHelper;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Before;
import org.junit.Test;

public final class SerializationTest {
  private static final String TOPIC = "topic";

  @Inject @MixIn /* package */ @MonotonicNonNull Serializer<SensorStateDuration> serializer;
  @Inject @MixIn /* package */ @MonotonicNonNull Deserializer<SensorStateDuration> deserializer;

  private static SensorStateDuration sampleSensorStateDuration() {
    var event =
        SensorState.builder()
            .id("7331")
            .time(Instant.ofEpochSecond(443634300L))
            .state(SensorState.State.ON)
            .build();
    return SensorStateDuration.builder().event(event).duration(Duration.ofSeconds(15)).build();
  }

  @Before
  public void before() {
    TestComponent.INSTANCE.inject(this);
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer"})
  public void canDecode() {
    var sensorState = sampleSensorStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  @RequiresNonNull("serializer")
  public void matchesSchema() throws IOException {
    var sensorState = sampleSensorStateDuration();
    var encoded = serializer.serialize(TOPIC, sensorState);

    var validationMessages = JsonTestHelper.validate(encoded);
    assertThat(validationMessages).isEmpty();
  }
}
