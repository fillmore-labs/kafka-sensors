package com.fillmore_labs.kafka.sensors.serde.confluent.json.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
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
  private static final String TOPIC = "topic";
  @Inject @Confluent /* package */ @MonotonicNonNull Serializer<SensorStateDurationJson> serializer;

  @Inject @Confluent /* package */ @MonotonicNonNull
  Deserializer<SensorStateDurationJson> deserializer;

  private static SensorStateDurationJson sampleSensorStateDuration() {
    var event =
        SensorStateJson.builder()
            .id("7331")
            .time(Instant.ofEpochSecond(443634300L))
            .state(SensorStateJson.State.ON)
            .build();
    return SensorStateDurationJson.builder().event(event).duration(Duration.ofSeconds(15)).build();
  }

  @Before
  public void before() {
    TestComponent.create().inject(this);
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer"})
  public void canDecode() {
    var sensorState = sampleSensorStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
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
