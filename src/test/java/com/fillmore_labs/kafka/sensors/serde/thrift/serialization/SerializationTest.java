package com.fillmore_labs.kafka.sensors.serde.thrift.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.thrift.v1.Position;
import com.fillmore_labs.kafka.sensors.thrift.v1.Reading;
import com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.thrift.protocol.TProtocolException;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  private final Serializer<StateDuration> serializer;
  private final Deserializer<StateDuration> deserializer;

  public SerializationTest() {
    var testComponent = TestComponent.create();
    this.serializer = testComponent.serializer();
    this.deserializer = testComponent.deserializer();
  }

  @Test
  public void canDecode() {
    var reading = new Reading();
    reading.setPosition(Position.ON);
    reading.setTime(INSTANT.toEpochMilli() * 1_000_0000L + 1L);

    var sensorState = new StateDuration();
    sensorState.setId("3771");
    sensorState.setReading(reading);
    sensorState.setDuration(Duration.ofSeconds(15).toNanos());

    var encoded = serializer.serialize(TOPIC, sensorState);

    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    var reading = new Reading();
    reading.setPosition(null);
    reading.setTime(INSTANT.toEpochMilli() * 1_000_0000L + 1L);

    var sensorState = new StateDuration();
    sensorState.setId("3771");
    sensorState.setReading(reading);
    sensorState.setDuration(Duration.ofSeconds(15).toNanos());

    var exception =
        assertThrows(SerializationException.class, () -> serializer.serialize(TOPIC, sensorState));
    assertThat(exception).hasCauseThat().isInstanceOf(TProtocolException.class);
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

    Serializer<StateDuration> serializer();

    Deserializer<StateDuration> deserializer();
  }
}
