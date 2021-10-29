package com.fillmore_labs.kafka.sensors.serde.proto.serialization;

import static com.google.common.truth.extensions.proto.ProtoTruth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.proto.v1.Event;
import com.fillmore_labs.kafka.sensors.proto.v1.StateDuration;
import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;
import dagger.Component;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.junit.Test;

public final class SerializationTest {
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
    var event =
        Event.newBuilder()
            .setTime(Timestamp.newBuilder().setSeconds(443634300L))
            .setPosition(Event.Position.POSITION_ON);

    var sensorState =
        StateDuration.newBuilder()
            .setId("3771")
            .setEvent(event)
            .setDuration(Duration.newBuilder().setSeconds(15L))
            .build();

    var encoded = serializer.serialize(TOPIC, sensorState);

    var decoded = deserializer.deserialize(TOPIC, encoded);

    assertThat(decoded).isEqualTo(sensorState);
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(
        NullPointerException.class,
        () ->
            Event.newBuilder()
                .setTime(Timestamp.newBuilder().setSeconds(443634300L))
                .setPosition(null)
                .build());
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
