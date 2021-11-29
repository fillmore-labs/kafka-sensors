package com.fillmore_labs.kafka.sensors.serde.confluent;

import com.fillmore_labs.kafka.sensors.serde.confluent.json.ConfluentJsonModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.proto.ConfluentProtoModule;
import dagger.Module;

@Module(
    includes = {ConfluentAvroModule.class, ConfluentProtoModule.class, ConfluentJsonModule.class})
public abstract class ConfluentModule {
  private ConfluentModule() {}
}
