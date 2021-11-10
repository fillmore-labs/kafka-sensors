package com.fillmore_labs.kafka.sensors.topology.context;

import com.fillmore_labs.kafka.sensors.serde.all_serdes.AllSerdesModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import java.util.Properties;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {AllSerdesModule.class, SchemaRegistryModule.class, TestComponent.TestModule.class})
public interface TestComponent {
  static TestComponent.Builder builder() {
    return DaggerTestComponent.builder();
  }

  Parameters parameters();

  SingleTestComponent.Builder singleTestComponentBuilder();

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder configuration(Properties configuration);

    TestComponent build();
  }

  @Module(subcomponents = SingleTestComponent.class)
  interface TestModule {}
}
