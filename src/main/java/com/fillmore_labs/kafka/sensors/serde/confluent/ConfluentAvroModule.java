package com.fillmore_labs.kafka.sensors.serde.confluent;

import com.fillmore_labs.kafka.sensors.serde.confluent.generic.ConfluentGenericModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.ConfluentReflectModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.specific.ConfluentSpecificModule;
import dagger.Module;

@Module(
    includes = {
      ConfluentReflectModule.class,
      ConfluentGenericModule.class,
      ConfluentSpecificModule.class,
    })
public abstract class ConfluentAvroModule {
  private ConfluentAvroModule() {}
}
