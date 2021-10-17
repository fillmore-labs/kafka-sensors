package com.fillmore_labs.kafka.sensors.topology.context;

import com.fillmore_labs.kafka.sensors.serde.all_serdes.AllSerdesModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AllSerdesModule.class})
public abstract class ParameterComponent {
  /* package */ ParameterComponent() {}

  public static Builder builder() {
    return DaggerParameterComponent.builder();
  }

  public abstract ParameterHelper parameters();

  @Component.Builder
  public interface Builder {
    @BindsInstance
    Builder schemaRegistryUrl(@SchemaRegistryUrl String schemaRegistryUrl);

    ParameterComponent build();
  }
}
