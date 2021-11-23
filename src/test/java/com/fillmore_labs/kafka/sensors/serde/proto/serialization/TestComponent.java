package com.fillmore_labs.kafka.sensors.serde.proto.serialization;

import com.fillmore_labs.kafka.sensors.proto.v1.StateDuration;
import dagger.Component;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Component(modules = TestModule.class)
public interface TestComponent {

  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  Serializer<StateDuration> serializer();

  Deserializer<StateDuration> deserializer();

  Serializer<com.fillmore_labs.kafka.sensors.test.proto.v1.StateDuration> testSerializer();

  Deserializer<com.fillmore_labs.kafka.sensors.test.proto.v1.StateDuration> testDeserializer();
}
