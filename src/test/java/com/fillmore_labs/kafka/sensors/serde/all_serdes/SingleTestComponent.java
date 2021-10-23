package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import dagger.BindsInstance;
import dagger.Subcomponent;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;

@Subcomponent(modules = SingleTestModule.class)
public interface SingleTestComponent {
  void injectMembers(@UnknownInitialization SerdeTestBase<SensorState> self);

  void injectMembersDuration(@UnknownInitialization SerdeTestBase<SensorStateDuration> self);

  @Subcomponent.Builder
  interface Builder {
    @BindsInstance
    Builder serializationFormat(@SingleTestModule.Serialization String format);

    @BindsInstance
    Builder deserializationFormat(@SingleTestModule.Deserialization String format);

    SingleTestComponent build();
  }
}
