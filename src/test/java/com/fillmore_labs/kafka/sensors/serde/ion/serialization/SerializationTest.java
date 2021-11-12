package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.serde.ion.serialization.ReadingIon.Position;
import dagger.Component;
import java.time.Duration;
import java.util.List;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
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
    var reading =
        ReadingIon.builder().time(443634300L * NANOS_PER_SECOND).position(Position.ON).build();
    return StateDurationIon.builder()
        .id("7331")
        .reading(reading)
        .duration(Duration.ofSeconds(15).toNanos())
        .build();
  }

  @Test
  public void canDecode() {
    var sensorState = sampleStateDuration();

    var encoded = serializer.serialize(TOPIC, sensorState);
    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  public void invalid() {
    var encoded = new byte[] {0x1};
    assertThrows(SerializationException.class, () -> deserializer.deserialize(TOPIC, encoded));
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
