package com.fillmore_labs.kafka.sensors.benchmark.context;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.SerdeStore;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {BenchModule.class})
public abstract class BenchComponent {
  /* package */ BenchComponent() {}

  public static Builder builder() {
    return DaggerBenchComponent.builder();
  }

  public abstract SerdeStore<SensorStateDuration> serdeStoreDuration();

  @Component.Builder
  public interface Builder {
    @BindsInstance
    Builder schemaRegistryUrl(@SchemaRegistryUrl String schemaRegistryUrl);

    BenchComponent build();
  }
}
