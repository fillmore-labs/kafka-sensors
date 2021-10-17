package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {SerializationModule.class})
public interface TestComponent {
  TestComponent INSTANCE = DaggerTestComponent.create();

  void inject(CanReadTest test);

  void inject(SerializationTest test);
}
