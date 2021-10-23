package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import dagger.Component;
import dagger.Module;
import javax.inject.Singleton;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;

@Singleton
@Component(modules = {TestComponent.TestModule.class})
public interface TestComponent {
  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  void injectMembers(@UnknownInitialization ConsistencyTestBase instance);

  Parameters parameters();

  @Module(
      includes = {AllSerdesModule.class, SchemaRegistryModule.class},
      subcomponents = SingleTestComponent.class)
  /* package */ abstract class TestModule {
    private TestModule() {}
  }
}
