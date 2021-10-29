package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.serde.avro.AvroModule;
import com.fillmore_labs.kafka.sensors.serde.avro.specific_faster.SpecificFasterModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.ConfluentModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.specific_faster.ConfluentSpecificFasterModule;
import com.fillmore_labs.kafka.sensors.serde.gson.GsonModule;
import com.fillmore_labs.kafka.sensors.serde.gson_faster.GsonFasterModule;
import com.fillmore_labs.kafka.sensors.serde.ion.IonModule;
import com.fillmore_labs.kafka.sensors.serde.json.JsonModule;
import com.fillmore_labs.kafka.sensors.serde.mixin.MixInModule;
import com.fillmore_labs.kafka.sensors.serde.proto.ProtoModule;
import com.fillmore_labs.kafka.sensors.serde.thrift.ThriftModule;
import dagger.Module;

@Module(
    includes = {
      AvroModule.class,
      ConfluentModule.class,
      ConfluentSpecificFasterModule.class,
      GsonFasterModule.class,
      GsonModule.class,
      IonModule.class,
      JsonModule.class,
      MixInModule.class,
      ProtoModule.class,
      SpecificFasterModule.class,
      ThriftModule.class,
    })
public abstract class AllSerdesModule {
  private AllSerdesModule() {}
}
