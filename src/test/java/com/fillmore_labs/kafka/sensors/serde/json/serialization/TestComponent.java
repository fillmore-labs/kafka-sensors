package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import dagger.Component;
import jakarta.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Singleton
@Component(modules = {SerializationModule.class})
public interface TestComponent {
  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  Deserializer<SensorStateJson> deserializer();

  Serializer<StateDurationJson> serializerDuration();

  Deserializer<StateDurationJson> deserializerDuration();
}
