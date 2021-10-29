package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.serde.ion.serialization.EventIon.Position;
import dagger.Component;
import java.time.Duration;
import java.util.List;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class SerializationTest {
  private static final long NANOS_PER_SECOND = 1_000_000_000L;
  private static final String TOPIC = "topic";

  private final Serializer<StateDurationIon> serializer;
  private final Deserializer<StateDurationIon> deserializer;

  public SerializationTest(
      String description,
      Serializer<StateDurationIon> serializer,
      Deserializer<StateDurationIon> deserializer) {
    this.serializer = serializer;
    this.deserializer = deserializer;
  }

  @Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> parameters() {
    var testComponent = TestComponent.create();

    var run1 = new Object[] {"binary", testComponent.serializer(), testComponent.deserializer()};
    var run2 = new Object[] {"text", testComponent.serializerText(), testComponent.deserializer()};
    return List.of(run1, run2);
  }

  private static StateDurationIon sampleStateDuration() {
    var event =
        EventIon.builder().time(443634300L * NANOS_PER_SECOND).position(Position.ON).build();
    return StateDurationIon.builder()
        .id("7331")
        .event(event)
        .duration(Duration.ofSeconds(15).toNanos())
        .build();
  }

  @Test
  @RequiresNonNull({"serializer", "deserializer"})
  public void canDecode() {
    var sensorState = sampleStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    @SerializationModule.Binary
    Serializer<StateDurationIon> serializer();

    @SerializationModule.Text
    Serializer<StateDurationIon> serializerText();

    Deserializer<StateDurationIon> deserializer();
  }
}
