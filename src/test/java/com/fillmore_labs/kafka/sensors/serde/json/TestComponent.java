package com.fillmore_labs.kafka.sensors.serde.json;

import dagger.Component;
import javax.inject.Singleton;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent {
  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  void inject(@UnknownInitialization SerdeDurationTest test);

  void inject(@UnknownInitialization SerdeTest test);
}
