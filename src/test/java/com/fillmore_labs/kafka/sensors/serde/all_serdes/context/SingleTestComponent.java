package com.fillmore_labs.kafka.sensors.serde.all_serdes.context;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import dagger.BindsInstance;
import dagger.Subcomponent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Subcomponent(modules = SingleTestModule.class)
public interface SingleTestComponent {
  Serializer<Event> serializerEvent();

  Deserializer<Event> deserializerEvent();

  Serializer<SensorState> serializer();

  Deserializer<SensorState> deserializer();

  Serializer<StateDuration> serializerDuration();

  Deserializer<StateDuration> deserializerDuration();

  @Subcomponent.Builder
  interface Builder {
    @BindsInstance
    Builder formats(SingleTestModule.Formats formats);

    SingleTestComponent build();
  }
}
