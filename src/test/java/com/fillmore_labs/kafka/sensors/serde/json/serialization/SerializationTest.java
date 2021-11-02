package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.json.JsonTestHelper;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.ReadingJson.Position;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Test;

public final class SerializationTest {
  private static final String TOPIC = "topic";

  private final Serializer<StateWithDurationJson> serializer;
  private final Deserializer<StateWithDurationJson> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializerDuration();
    this.deserializer = testComponent.deserializerDuration();
  }

  private static StateWithDurationJson sampleStateDuration() {
    var reading =
        ReadingJson.builder().time(Instant.ofEpochSecond(443634300L)).position(Position.ON).build();
    return StateWithDurationJson.builder()
        .id("7331")
        .reading(reading)
        .duration(Duration.ofSeconds(15))
        .build();
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer"})
  public void canDecode() {
    var sensorState = sampleStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  @RequiresNonNull("serializer")
  public void matchesSchema() throws IOException {
    var sensorState = sampleStateDuration();
    var encoded = serializer.serialize(TOPIC, sensorState);

    var validationMessages = JsonTestHelper.validate(encoded);
    assertThat(validationMessages).isEmpty();
  }
}
