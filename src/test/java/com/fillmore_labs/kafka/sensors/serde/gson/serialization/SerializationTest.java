package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.json.JsonTestHelper;
import com.fillmore_labs.kafka.sensors.serde.converter.DurationDecimalHelper;
import com.fillmore_labs.kafka.sensors.serde.converter.InstantDecimalHelper;
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

  @Inject /* package */ @MonotonicNonNull Serializer<SensorStateDurationGson> serializer;
  @Inject /* package */ @MonotonicNonNull Deserializer<SensorStateDurationGson> deserializer;

  private static SensorStateDurationGson sampleSensorStateDuration() {
    return SensorStateDurationGson.builder()
        .id("7331")
        .time(InstantDecimalHelper.instant2Decimal(Instant.ofEpochSecond(443634300L)))
        .state(SensorStateGson.State.ON)
        .duration(DurationDecimalHelper.duration2Decimal(Duration.ofSeconds(15)))
        .build();
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
