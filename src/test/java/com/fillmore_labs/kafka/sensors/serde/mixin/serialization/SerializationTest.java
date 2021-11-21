package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.helper.json.JsonTestHelper;
import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public final class SerializationTest {
  private static final String TOPIC = "topic";

  private final Serializer<StateDuration> serializer;
  private final Deserializer<StateDuration> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializerDuration();
    this.deserializer = testComponent.deserializerDuration();
  }

  private static StateDuration sampleStateDuration() {
    var reading =
        Reading.builder()
            .time(Instant.ofEpochSecond(443_634_300L))
            .position(Reading.Position.ON)
            .build();
    return StateDuration.builder()
        .id("7331")
        .reading(reading)
        .duration(Duration.ofSeconds(15))
        .build();
  }

  @Test
  public void canDecode() {
    var sensorState = sampleStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public void matchesSchema() throws IOException {
    var sensorState = sampleStateDuration();
    var encoded = serializer.serialize(TOPIC, sensorState);

    var validationMessages = JsonTestHelper.validate(encoded);
    assertThat(validationMessages).isEmpty();
  }

  @Test
  public void nullEncoding() {
    @SuppressWarnings("nullness:argument") // Serializer is not annotated
    var encoded = serializer.serialize(TOPIC, null);

    assertThat(encoded).isNull();
  }

  @Test
  public void nullDecoding() {
    @SuppressWarnings("nullness:argument") // Deserializer is not annotated
    var decoded = deserializer.deserialize(TOPIC, null);

    assertThat(decoded).isNull();
  }

  @Test
  public void invalid() {
    var encoded = "false".getBytes(StandardCharsets.UTF_8);
    assertThrows(SerializationException.class, () -> deserializer.deserialize(TOPIC, encoded));
  }
}
