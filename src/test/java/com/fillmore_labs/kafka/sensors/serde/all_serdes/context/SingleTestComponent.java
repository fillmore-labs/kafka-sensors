package com.fillmore_labs.kafka.sensors.serde.all_serdes.context;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import dagger.BindsInstance;
import dagger.Subcomponent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Subcomponent(modules = SingleTestModule.class)
public interface SingleTestComponent {
  Serializer<SensorState> serializer();

  Deserializer<SensorState> deserializer();

  Serializer<SensorStateDuration> serializerDuration();

  Deserializer<SensorStateDuration> deserializerDuration();

  @Subcomponent.Builder
  interface Builder {
    @BindsInstance
    Builder serializationFormat(@SingleTestModule.Serialization String format);

    @BindsInstance
    Builder deserializationFormat(@SingleTestModule.Deserialization String format);

    SingleTestComponent build();
  }
}
