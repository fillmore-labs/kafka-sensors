package com.fillmore_labs.kafka.sensors.serde.avro.specific.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.avro.SensorState;
import com.fillmore_labs.kafka.sensors.avro.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.avro.State;
import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationMicroHelper;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.avro.AvroMissingFieldException;
import org.apache.avro.AvroRuntimeException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Before;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  @Inject /* package */ @MonotonicNonNull Serializer<SensorStateDuration> serializer;
  @Inject /* package */ @MonotonicNonNull Deserializer<SensorStateDuration> deserializer;

  @Before
  public void before() {
    var testComponent = TestComponent.create();
    testComponent.inject(this);
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer"})
  public void canDecode() {
    var event = SensorState.newBuilder().setId("7331").setTime(INSTANT).setState(State.ON);

    var sensorState =
        SensorStateDuration.newBuilder()
            .setEventBuilder(event)
            .setDuration(DurationMicroHelper.duration2Micros(Duration.ofSeconds(15)))
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
  public void stateIsRequired() {
    var event = SensorState.newBuilder().setId("7331").setTime(INSTANT);

    assertThrows(
        AvroMissingFieldException.class,
        () ->
            SensorStateDuration.newBuilder()
                .setEventBuilder(event)
                .setDuration(DurationMicroHelper.duration2Micros(Duration.ofSeconds(15)))
                .build());
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(
        AvroRuntimeException.class,
        () -> SensorState.newBuilder().setId("7331").setTime(INSTANT).setState(null).build());
  }

  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    void inject(SerializationTest test);
  }
}
