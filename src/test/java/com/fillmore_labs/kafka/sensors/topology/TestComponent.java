package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.topology.server.EmbeddedKafka;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = TestModule.class)
public interface TestComponent {
  static TestComponent.Builder builder() {
    return DaggerTestComponent.builder();
  }

  void inject(TopologyTest test);

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder embeddedKafka(EmbeddedKafka embeddedKafka);

    TestComponent build();
  }
}
