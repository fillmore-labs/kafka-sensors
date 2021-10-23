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
import org.junit.Test;

public final class SerializationTest {
  private static final String TOPIC = "topic";

  @Inject @MixIn /* package */ Serializer<SensorStateDuration> serializer;
  @Inject @MixIn /* package */ Deserializer<SensorStateDuration> deserializer;

  public SerializationTest() {
    TestComponent.create().inject(this);
    assert serializer != null : "@AssumeAssertion(nullness): inject() failed";
    assert deserializer != null : "@AssumeAssertion(nullness): inject() failed";
  }

  private static SensorStateDuration sampleSensorStateDuration() {
    var event =
        SensorState.builder()
            .id("7331")
            .time(Instant.ofEpochSecond(443634300L))
            .state(SensorState.State.ON)
            .build();
    return SensorStateDuration.builder().event(event).duration(Duration.ofSeconds(15)).build();
  }

  @Test
  public void canDecode() {
    var sensorState = sampleSensorStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public void matchesSchema() throws IOException {
    var sensorState = sampleSensorStateDuration();
    var encoded = serializer.serialize(TOPIC, sensorState);

    var validationMessages = JsonTestHelper.validate(encoded);
    assertThat(validationMessages).isEmpty();
  }
}
