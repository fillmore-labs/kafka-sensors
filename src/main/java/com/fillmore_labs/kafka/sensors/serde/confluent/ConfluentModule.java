package com.fillmore_labs.kafka.sensors.serde.confluent;

import com.fillmore_labs.kafka.sensors.serde.confluent.generic.ConfluentGenericModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.json.ConfluentJsonModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.proto.ConfluentProtoModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.ConfluentReflectModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.specific.ConfluentSpecificModule;
import dagger.Module;

@Module(
    includes = {
      ConfluentReflectModule.class,
      ConfluentGenericModule.class,
      ConfluentSpecificModule.class,
      ConfluentProtoModule.class,
      ConfluentJsonModule.class
    })
public abstract class ConfluentModule {
  private ConfluentModule() {}
}
