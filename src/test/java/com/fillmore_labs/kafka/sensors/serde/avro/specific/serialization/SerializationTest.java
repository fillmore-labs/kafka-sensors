package com.fillmore_labs.kafka.sensors.serde.avro.specific.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.avro.Position;
import com.fillmore_labs.kafka.sensors.avro.Reading;
import com.fillmore_labs.kafka.sensors.avro.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.InstantNanosHelper;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import org.apache.avro.AvroMissingFieldException;
import org.apache.avro.AvroRuntimeException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public final class SerializationTest {
  private static final long TIME =
      InstantNanosHelper.instant2Nanos(Instant.ofEpochSecond(443_634_300L));
  private static final String TOPIC = "topic";

  private final Serializer<StateDuration> serializer;
  private final Deserializer<StateDuration> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializer();
    this.deserializer = testComponent.deserializer();
  }

  @Test
  public void canDecode() {
    var reading = Reading.newBuilder().setTime(TIME).setPosition(Position.ON);

    var sensorState =
        StateDuration.newBuilder()
            .setId("7331")
            .setReadingBuilder(reading)
            .setDuration(Duration.ofSeconds(15).toNanos())
            .build();

    var encoded = serializer.serialize(TOPIC, sensorState);

    // Check for single-record format (version 1) marker
    // https://avro.apache.org/docs/current/spec.html#single_object_encoding
    assertThat(encoded[0]).isEqualTo((byte) 0xc3);
    assertThat(encoded[1]).isEqualTo((byte) 0x01);

    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public void positionIsRequired() {
    var reading = Reading.newBuilder().setTime(TIME);

    assertThrows(
        AvroMissingFieldException.class,
        () ->
            StateDuration.newBuilder()
                .setId("7331")
                .setReadingBuilder(reading)
                .setDuration(Duration.ofSeconds(15).toNanos())
                .build());
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(
        AvroRuntimeException.class,
        () -> Reading.newBuilder().setTime(TIME).setPosition(null).build());
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

  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    Serializer<StateDuration> serializer();

    Deserializer<StateDuration> deserializer();
  }
}
