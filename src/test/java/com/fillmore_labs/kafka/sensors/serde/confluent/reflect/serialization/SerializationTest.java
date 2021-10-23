package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Before;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  @Inject /* package */ @MonotonicNonNull Serializer<SensorStateDurationReflect> serializer;
  @Inject /* package */ @MonotonicNonNull Deserializer<SensorStateDurationReflect> deserializer;

  @Before
  public void before() {
    TestComponent.create().inject(this);
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer"})
  public void canDecode() {
    var event = new SensorStateReflect();
    event.id = "7331";
    event.time = INSTANT;
    event.state = SensorStateReflect.State.ON;

    var sensorState = new SensorStateDurationReflect();
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
  public void stateIsRequired() {
    var event = new SensorStateReflect();
    event.id = "7331";
    event.time = INSTANT;

    var sensorState = new SensorStateDurationReflect();
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

    void inject(SerializationTest test);
  }
}
