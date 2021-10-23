package com.fillmore_labs.kafka.sensors.benchmark;

import com.fillmore_labs.kafka.sensors.serde.all_serdes.AllSerdesModule;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;

@Singleton
@Component(modules = {BenchModule.class, AllSerdesModule.class})
public interface BenchComponent {
  static Builder builder() {
    return DaggerBenchComponent.builder();
  }

  void injectMembers(@UnknownInitialization ExecutionPlan state);

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder format(@BenchModule.Format String format);

    BenchComponent build();
  }
}
