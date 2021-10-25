package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
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

  @MixIn
  Deserializer<SensorState> deserializer();

  @MixIn
  Serializer<SensorStateDuration> serializerDuration();

  @MixIn
  Deserializer<SensorStateDuration> deserializerDuration();
}
