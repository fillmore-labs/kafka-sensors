package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Singleton;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  private final Serializer<StateDurationReflect> serializer;
  private final Deserializer<StateDurationReflect> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializer();
    this.deserializer = testComponent.deserializer();
  }

  @Test
  public void canDecode() {
    var event = new EventReflect();
    event.time = INSTANT;
    event.position = EventReflect.Position.ON;

    var sensorState = new StateDurationReflect();
    sensorState.id = "7331";
    sensorState.event = event;
    sensorState.duration = Duration.ofSeconds(15);

    var encoded = serializer.serialize(TOPIC, sensorState);

    // Check for “Magic Byte”
    // https://docs.confluent.io/current/schema-registry/serializer-formatter.html#wire-format
    assertThat(encoded[0]).isEqualTo((byte) 0);

    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  @RequiresNonNull("serializer")
  @SuppressWarnings("nullness:assignment")
  public void positionIsRequired() {
    var event = new EventReflect();
    event.time = INSTANT;
    event.position = null;

    var sensorState = new StateDurationReflect();
    sensorState.event = event;
    sensorState.duration = Duration.ofSeconds(15);

    var ser = serializer; // effectively final (JLS §4.12.4)
    assertThrows(SerializationException.class, () -> ser.serialize(TOPIC, sensorState));
  }

  @Singleton
  @Component(modules = {SerializationModule.class, SchemaRegistryModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    Serializer<StateDurationReflect> serializer();

    Deserializer<StateDurationReflect> deserializer();
  }
}
