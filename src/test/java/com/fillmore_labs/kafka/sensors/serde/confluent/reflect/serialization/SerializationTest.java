package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.helper.confluent.SchemaRegistryRule;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import dagger.BindsInstance;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

public final class SerializationTest {
  @ClassRule
  public static final SchemaRegistryRule REGISTRY_TEST_RESOURCE = new SchemaRegistryRule();

  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  @Inject /* package */ @MonotonicNonNull Serializer<SensorStateDurationReflect> serializer;
  @Inject /* package */ @MonotonicNonNull Deserializer<SensorStateDurationReflect> deserializer;

  @Before
  public void before() {
    var testComponent =
        TestComponent.builder().schemaRegistryUrl(REGISTRY_TEST_RESOURCE.registryUrl()).build();
    testComponent.inject(this);
  }

  @After
  public void after() {
    REGISTRY_TEST_RESOURCE.reset();
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

  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static Builder builder() {
      return DaggerSerializationTest_TestComponent.builder();
    }

    void inject(SerializationTest test);

    @Component.Builder
    interface Builder {
      @BindsInstance
      Builder schemaRegistryUrl(@SchemaRegistryUrl String schemaRegistryUrl);

      TestComponent build();
    }
  }
}
