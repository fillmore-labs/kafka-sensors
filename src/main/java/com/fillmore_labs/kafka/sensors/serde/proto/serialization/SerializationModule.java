package com.fillmore_labs.kafka.sensors.serde.proto.serialization;

import com.fillmore_labs.kafka.sensors.proto.v1.Reading;
import com.fillmore_labs.kafka.sensors.proto.v1.SensorState;
import com.fillmore_labs.kafka.sensors.proto.v1.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.serializer.proto.ProtoDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.proto.ProtoSerializer;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  /* package */ static Serializer<Reading> readingSerializer() {
    return new ProtoSerializer<>();
  }

  @Provides
  /* package */ static Deserializer<Reading> readingDeserializer() {
    return new ProtoDeserializer<>(Reading.parser());
  }

  @Provides
  /* package */ static Serializer<SensorState> sensorStateSerializer() {
    return new ProtoSerializer<>();
  }

  @Provides
  /* package */ static Deserializer<SensorState> sensorStateDeserializer() {
    return new ProtoDeserializer<>(SensorState.parser());
  }

  @Provides
  /* package */ static Serializer<StateDuration> stateDurationSerializer() {
    return new ProtoSerializer<>();
  }

  @Provides
  /* package */ static Deserializer<StateDuration> stateDurationDeserializer() {
    return new ProtoDeserializer<>(StateDuration.parser());
  }
}
