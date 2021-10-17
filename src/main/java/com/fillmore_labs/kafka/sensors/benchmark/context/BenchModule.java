package com.fillmore_labs.kafka.sensors.benchmark.context;

import com.fillmore_labs.kafka.sensors.serde.all_serdes.AllSerdesModule;
import dagger.Module;

@Module(includes = AllSerdesModule.class)
/* package */ abstract class BenchModule {
  private BenchModule() {}
}
