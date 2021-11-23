package com.fillmore_labs.kafka.sensors.serde.thrift.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.thrift.ThriftDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.thrift.ThriftSerializer;
import com.fillmore_labs.kafka.sensors.test.thrift.v1.StateDuration;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = SerializationModule.class)
public abstract class TestModule {
  private TestModule() {}

  @Provides
  /* package */ static Serializer<StateDuration> stateDurationSerializer() {
    return new ThriftSerializer<>();
  }

  @Provides
  /* package */ static Deserializer<StateDuration> stateDurationDeserializer() {
    return new ThriftDeserializer<>(StateDuration::new);
  }
}
