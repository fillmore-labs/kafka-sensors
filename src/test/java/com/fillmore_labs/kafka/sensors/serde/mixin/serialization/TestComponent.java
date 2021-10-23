package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import dagger.Component;
import javax.inject.Singleton;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;

@Singleton
@Component(modules = {SerializationModule.class})
public interface TestComponent {
  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  void inject(@UnknownInitialization CanReadTest test);

  void inject(@UnknownInitialization SerializationTest test);
}
