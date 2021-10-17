package com.fillmore_labs.kafka.sensors.app.context;

import com.fillmore_labs.kafka.sensors.streams.StreamsModule;
import com.fillmore_labs.kafka.sensors.topology.TopologyModule;
import dagger.Module;

/** Provides the bindings for our main application. */
@Module(
    includes = {
      ConfigurationModule.class,
      AppSerDesModule.class,
      StartUpModule.class,
      StreamsModule.class,
      TopologyModule.class
    })
/* package */ abstract class MainModule {
  private MainModule() {}
}
