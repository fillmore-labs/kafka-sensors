package com.fillmore_labs.kafka.sensors.serde.thrift.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.thrift.ThriftDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.thrift.ThriftSerializer;
import com.fillmore_labs.kafka.sensors.thrift.v1.Event;
import com.fillmore_labs.kafka.sensors.thrift.v1.SensorState;
import com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  /* package */ static Serializer<Event> eventSerializer() {
    return new ThriftSerializer<>();
  }

  @Provides
  /* package */ static Deserializer<Event> eventDeserializer() {
    return new ThriftDeserializer<>(Event::new);
  }

  @Provides
  /* package */ static Serializer<SensorState> sensorStateSerializer() {
    return new ThriftSerializer<>();
  }

  @Provides
  /* package */ static Deserializer<SensorState> sensorStateDeserializer() {
    return new ThriftDeserializer<>(SensorState::new);
  }

  @Provides
  /* package */ static Serializer<StateDuration> stateDurationSerializer() {
    return new ThriftSerializer<>();
  }

  @Provides
  /* package */ static Deserializer<StateDuration> stateDurationDeserializer() {
    return new ThriftDeserializer<>(StateDuration::new);
  }
}
