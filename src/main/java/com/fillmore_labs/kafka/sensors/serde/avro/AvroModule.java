package com.fillmore_labs.kafka.sensors.serde.avro;

import com.fillmore_labs.kafka.sensors.serde.avro.generic.GenericModule;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.ReflectModule;
import com.fillmore_labs.kafka.sensors.serde.avro.specific.SpecificModule;
import dagger.Module;

@Module(includes = {SpecificModule.class, GenericModule.class, ReflectModule.class})
public abstract class AvroModule {
  private AvroModule() {}
}
