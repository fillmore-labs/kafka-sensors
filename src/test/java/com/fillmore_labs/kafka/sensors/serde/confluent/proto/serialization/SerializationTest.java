package com.fillmore_labs.kafka.sensors.serde.confluent.proto.serialization;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.extensions.proto.ProtoTruth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import com.fillmore_labs.kafka.sensors.serde.proto.serialization.ProtoTypesMapper;
import com.fillmore_labs.kafka.sensors.v1.SensorState;
import com.fillmore_labs.kafka.sensors.v1.SensorState.State;
import com.fillmore_labs.kafka.sensors.v1.SensorStateDuration;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Before;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  @Inject @Confluent /* package */ @MonotonicNonNull Serializer<SensorStateDuration> serializer;
  @Inject @Confluent /* package */ @MonotonicNonNull Deserializer<SensorStateDuration> deserializer;

  @Before
  public void before() {
    TestComponent.create().inject(this);
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer"})
  public void canDecode() {
    var event =
        SensorState.newBuilder()
            .setId("7331")
            .setTime(ProtoTypesMapper.instant2Timestamp(INSTANT))
            .setState(State.STATE_ON);

    var sensorState =
        SensorStateDuration.newBuilder()
            .setEvent(event)
            .setDuration(ProtoTypesMapper.duration2Duration(Duration.ofSeconds(15)))
            .build();

    var encoded = serializer.serialize(TOPIC, sensorState);

    // Check for “Magic Byte”
    // https://docs.confluent.io/current/schema-registry/serializer-formatter.html#wire-format
    assertThat(encoded[0]).isEqualTo((byte) 0);

    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(
        NullPointerException.class,
        () ->
            SensorState.newBuilder()
                .setId("7331")
                .setTime(ProtoTypesMapper.instant2Timestamp(INSTANT))
                .setState(null)
                .build());
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
