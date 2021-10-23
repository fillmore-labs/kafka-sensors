package com.fillmore_labs.kafka.sensors.topology;

import dagger.Module;
import dagger.Provides;
import org.apache.kafka.streams.Topology;

/** Provides the single {@link Topology} of our Kafka streams example. */
@Module
public abstract class TopologyModule {
  private TopologyModule() {}

  @Provides
  /* package */ static Topology topology(TopologyFactory factory) {
    return factory.get();
  }
}
