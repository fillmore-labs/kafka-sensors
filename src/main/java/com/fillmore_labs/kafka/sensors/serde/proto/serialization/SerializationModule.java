package com.fillmore_labs.kafka.sensors.serde.proto.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.proto.ProtoDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.proto.ProtoSerializer;
import com.fillmore_labs.kafka.sensors.v1.SensorState;
import com.fillmore_labs.kafka.sensors.v1.SensorStateDuration;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorState> sensorStateSerializer() {
    return new ProtoSerializer<>();
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorState> sensorStateDeserializer() {
    return new ProtoDeserializer<>(SensorState.parser());
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateDuration> sensorStateDurationSerializer() {
    return new ProtoSerializer<>();
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateDuration> sensorStateDurationDeserializer() {
    return new ProtoDeserializer<>(SensorStateDuration.parser());
  }
}
