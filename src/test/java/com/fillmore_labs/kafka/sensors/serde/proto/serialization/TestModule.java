package com.fillmore_labs.kafka.sensors.serde.proto.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.proto.ProtoDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.proto.ProtoSerializer;
import com.fillmore_labs.kafka.sensors.test.proto.v1.StateDuration;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = SerializationModule.class)
public abstract class TestModule {
  private TestModule() {}

  @Provides
  /* package */ static Serializer<StateDuration> stateDurationSerializer() {
    return new ProtoSerializer<>();
  }

  @Provides
  /* package */ static Deserializer<StateDuration> stateDurationDeserializer() {
    return new ProtoDeserializer<>(StateDuration.parser());
  }
}
