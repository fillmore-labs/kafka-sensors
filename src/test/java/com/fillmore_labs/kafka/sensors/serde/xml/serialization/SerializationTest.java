package com.fillmore_labs.kafka.sensors.serde.xml.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import dagger.Component;
import jakarta.inject.Singleton;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public final class SerializationTest {
  private static final String TOPIC = "topic";

  private final Serializer<StateDurationXml> serializer;
  private final Deserializer<StateDurationXml> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializer();
    this.deserializer = testComponent.deserializer();
  }

  private static StateDurationXml sampleStateDuration() {
    var reading = new ReadingXml();
    reading.position = ReadingXml.Position.ON;
    reading.time = Instant.ofEpochSecond(443_634_300L);

    var stateDuration = new StateDurationXml();
    stateDuration.id = "7331";
    stateDuration.reading = reading;
    stateDuration.duration = Duration.ofSeconds(15);

    return stateDuration;
  }

  @Test
  public void canDecode() {
    var sensorState = sampleStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
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
    var encoded = new byte[] {0x1};
    assertThrows(SerializationException.class, () -> deserializer.deserialize(TOPIC, encoded));
  }

  @Singleton
  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    Serializer<StateDurationXml> serializer();

    Deserializer<StateDurationXml> deserializer();
  }
}
