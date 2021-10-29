package com.fillmore_labs.kafka.sensors.serde.ion;

import com.fillmore_labs.kafka.sensors.serde.ion.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SerializationModule;
import dagger.Module;

@Module(
    includes = {
      IonTextModule.class,
      IonBinaryModule.class,
      SerializationModule.class,
      MapperModule.class
    })
public abstract class IonModule {
  private IonModule() {}
}
