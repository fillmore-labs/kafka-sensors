package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import dagger.Component;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Singleton
@Component(modules = {SerializationModule.class})
public interface TestComponent {
  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  Deserializer<SensorStateGson> deserializer();

  Serializer<StateDurationGson> serializerDuration();

  Deserializer<StateDurationGson> deserializerDuration();
}
