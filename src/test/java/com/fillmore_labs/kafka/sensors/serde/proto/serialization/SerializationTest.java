package com.fillmore_labs.kafka.sensors.serde.proto.serialization;

import static com.google.common.truth.extensions.proto.ProtoTruth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.v1.SensorState;
import com.fillmore_labs.kafka.sensors.v1.SensorState.State;
import com.fillmore_labs.kafka.sensors.v1.SensorStateDuration;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.junit.Test;

public final class SerializationTest {
  private static final Instant INSTANT = Instant.ofEpochSecond(443634300L);
  private static final String TOPIC = "topic";

  @Inject /* package */ Serializer<SensorStateDuration> serializer;
  @Inject /* package */ Deserializer<SensorStateDuration> deserializer;

  public SerializationTest() {
    TestComponent.create().inject(this);
    assert serializer != null : "@AssumeAssertion(nullness): inject() failed";
    assert deserializer != null : "@AssumeAssertion(nullness): inject() failed";
  }

  @Test
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

  @Component(modules = {SerializationModule.class})
  public interface TestComponent {
    static TestComponent create() {
      return DaggerSerializationTest_TestComponent.create();
    }

    void inject(@UnknownInitialization SerializationTest test);
  }
}
