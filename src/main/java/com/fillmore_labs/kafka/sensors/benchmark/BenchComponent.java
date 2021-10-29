package com.fillmore_labs.kafka.sensors.benchmark;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.all_serdes.AllSerdesModule;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Singleton
@Component(modules = {BenchModule.class, AllSerdesModule.class})
public interface BenchComponent {
  static Builder builder() {
    return DaggerBenchComponent.builder();
  }

  Serializer<StateDuration> serializer();

  Deserializer<StateDuration> deserializer();

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder format(@BenchModule.Format String format);

    BenchComponent build();
  }
}
