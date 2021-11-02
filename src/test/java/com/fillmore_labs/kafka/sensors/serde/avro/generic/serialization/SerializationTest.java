package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import org.apache.avro.AvroMissingFieldException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  private final Serializer<GenericRecord> serializer;
  private final Deserializer<GenericRecord> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializer();
    this.deserializer = testComponent.deserializer();
  }

  @Test
  public void canDecode() {
    var reading =
        new GenericRecordBuilder(ReadingSchema.SCHEMA)
            .set(ReadingSchema.FIELD_TIME, INSTANT)
            .set(ReadingSchema.FIELD_POSITION, PositionSchema.ENUM_OFF)
            .build();

    var sensorState =
        new GenericRecordBuilder(StateDurationSchema.SCHEMA)
            .set(StateDurationSchema.FIELD_ID, "7331")
            .set(StateDurationSchema.FIELD_READING, reading)
            .set(StateDurationSchema.FIELD_DURATION, Duration.ofSeconds(15))
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
    assertThrows(
        AvroMissingFieldException.class,
        () ->
            new GenericRecordBuilder(ReadingSchema.SCHEMA)
                .set(ReadingSchema.FIELD_TIME, INSTANT)
                .build());
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(
        AvroRuntimeException.class,
        () ->
            new GenericRecordBuilder(ReadingSchema.SCHEMA)
                .set(ReadingSchema.FIELD_TIME, INSTANT)
                .set(ReadingSchema.FIELD_POSITION, null)
                .build());
  }

  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    @Avro.StateDuration
    Serializer<GenericRecord> serializer();

    @Avro.StateDuration
    Deserializer<GenericRecord> deserializer();
  }
}
