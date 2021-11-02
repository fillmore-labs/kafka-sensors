package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.json.JsonTestHelper;
import com.fillmore_labs.kafka.sensors.serde.converter.DurationDecimalHelper;
import com.fillmore_labs.kafka.sensors.serde.converter.InstantDecimalHelper;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.ReadingGson.Position;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Test;

public final class SerializationTest {
  private static final String TOPIC = "topic";

  private final Serializer<StateDurationGson> serializer;
  private final Deserializer<StateDurationGson> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializerDuration();
    this.deserializer = testComponent.deserializerDuration();
  }

  private static StateDurationGson sampleStateDuration() {
    return StateDurationGson.builder()
        .id("7331")
        .time(InstantDecimalHelper.instant2Decimal(Instant.ofEpochSecond(443634300L)))
        .position(Position.ON)
        .duration(DurationDecimalHelper.duration2Decimal(Duration.ofSeconds(15)))
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
