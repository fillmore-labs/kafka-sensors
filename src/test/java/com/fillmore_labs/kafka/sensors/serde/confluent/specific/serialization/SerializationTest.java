package com.fillmore_labs.kafka.sensors.serde.confluent.specific.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.avro.Position;
import com.fillmore_labs.kafka.sensors.avro.Reading;
import com.fillmore_labs.kafka.sensors.avro.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Singleton;
import org.apache.avro.AvroMissingFieldException;
import org.apache.avro.AvroRuntimeException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
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
    var reading =
        Reading.newBuilder()
            .setTime(INSTANT.toEpochMilli() * 1_000_000L + 1L)
            .setPosition(Position.ON);

    var sensorState =
        StateDuration.newBuilder()
            .setId("7331")
            .setReadingBuilder(reading)
            .setDuration(Duration.ofSeconds(15).toNanos())
            .build();

    var encoded = serializer.serialize(TOPIC, sensorState);

    // Check for “Magic Byte”
    // https://docs.confluent.io/current/schema-registry/serializer-formatter.html#wire-format
    assertThat(encoded[0]).isEqualTo((byte) 0);

    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public void positionIsRequired() {
    var reading = Reading.newBuilder().setTime(INSTANT.toEpochMilli() * 1_000_000L + 1L);

    assertThrows(
        AvroMissingFieldException.class,
        () ->
            StateDuration.newBuilder()
                .setReadingBuilder(reading)
                .setDuration(Duration.ofSeconds(15).toNanos())
                .build());
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(
        AvroRuntimeException.class,
        () ->
            Reading.newBuilder()
                .setTime(INSTANT.toEpochMilli() * 1_000_000L + 1L)
                .setPosition(null)
                .build());
  }

  @Singleton
  @Component(modules = {SerializationModule.class, SchemaRegistryModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    @Confluent
    Serializer<StateDuration> serializer();

    @Confluent
    Deserializer<StateDuration> deserializer();
  }
}
