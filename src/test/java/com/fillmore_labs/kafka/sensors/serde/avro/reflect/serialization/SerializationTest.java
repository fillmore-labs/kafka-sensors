package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.avro.AvroTypeException;
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
    var testComponent = TestComponent.create();
    testComponent.inject(this);
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

    // Check for single-record format (version 1) marker
    // https://avro.apache.org/docs/current/spec.html#single_object_encoding
    assertThat(encoded[0]).isEqualTo((byte) 0xc3);
    assertThat(encoded[1]).isEqualTo((byte) 0x01);

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
    assertThrows(AvroTypeException.class, () -> ser.serialize(TOPIC, sensorState));
  }

  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    void inject(SerializationTest test);
  }
}
