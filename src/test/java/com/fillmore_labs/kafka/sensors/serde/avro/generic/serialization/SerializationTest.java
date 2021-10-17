package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.avro.AvroMissingFieldException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Before;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  @Inject @Avro.SensorStateDuration /* package */ @MonotonicNonNull
  Serializer<GenericRecord> serializer;

  @Inject @Avro.SensorStateDuration /* package */ @MonotonicNonNull
  Deserializer<GenericRecord> deserializer;

  @Before
  public void before() {
    var testComponent = TestComponent.create();
    testComponent.inject(this);
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer"})
  public void canDecode() {
    var event =
        new GenericRecordBuilder(SensorStateSchema.SCHEMA)
            .set(SensorStateSchema.FIELD_ID, "7331")
            .set(SensorStateSchema.FIELD_TIME, INSTANT)
            .set(SensorStateSchema.FIELD_STATE, SensorStateStateSchema.ENUM_OFF)
            .build();

    var sensorState =
        new GenericRecordBuilder(SensorStateDurationSchema.SCHEMA)
            .set(SensorStateDurationSchema.FIELD_EVENT, event)
            .set(SensorStateDurationSchema.FIELD_DURATION, Duration.ofSeconds(15))
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
  @RequiresNonNull("serializer")
  public void stateIsRequired() {
    assertThrows(
        AvroMissingFieldException.class,
        () ->
            new GenericRecordBuilder(SensorStateSchema.SCHEMA)
                .set(SensorStateSchema.FIELD_ID, "7331")
                .set(SensorStateSchema.FIELD_TIME, INSTANT)
                .build());
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(
        AvroRuntimeException.class,
        () ->
            new GenericRecordBuilder(SensorStateSchema.SCHEMA)
                .set(SensorStateSchema.FIELD_ID, "7331")
                .set(SensorStateSchema.FIELD_TIME, INSTANT)
                .set(SensorStateSchema.FIELD_STATE, null)
                .build());
  }

  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    void inject(SerializationTest test);
  }
}
