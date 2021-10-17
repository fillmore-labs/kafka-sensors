package com.fillmore_labs.kafka.sensors.serde.confluent;

import com.fillmore_labs.kafka.sensors.serde.confluent.generic.GenericModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.json.JsonModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.proto.ProtoModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.ReflectModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.specific.SpecificModule;
import dagger.Module;

@Module(
    includes = {
      ReflectModule.class,
      GenericModule.class,
      SpecificModule.class,
      ProtoModule.class,
      JsonModule.class
    })
public abstract class ConfluentModule {
  private ConfluentModule() {}
}
