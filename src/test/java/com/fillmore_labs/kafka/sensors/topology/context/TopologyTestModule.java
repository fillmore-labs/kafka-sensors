package com.fillmore_labs.kafka.sensors.topology.context;

import dagger.Module;
import dagger.Provides;
import java.util.Properties;
import javax.inject.Singleton;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;

@Module
public abstract class TopologyTestModule {
  private TopologyTestModule() {}

  @Provides
  @Singleton
  @SuppressWarnings("CloseableProvides")
  /* package */ static TopologyTestDriver topologyTestDriver(
      Topology topology, Properties settings) {
    return new TopologyTestDriver(topology, settings);
  }
}
