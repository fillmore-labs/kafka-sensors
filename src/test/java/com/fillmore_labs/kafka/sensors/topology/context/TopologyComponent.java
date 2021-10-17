package com.fillmore_labs.kafka.sensors.topology.context;

import com.fillmore_labs.kafka.sensors.configuration.App;
import com.fillmore_labs.kafka.sensors.configuration.KafkaConfiguration;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.topology.TopologyModule;
import dagger.BindsInstance;
import dagger.Component;
import java.util.Properties;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.TopologyTestDriver;

@Singleton
@Component(modules = {TopologyModule.class, TopologyTestModule.class})
public abstract class TopologyComponent {
  /* package */ TopologyComponent() {}

  public static Builder builder() {
    return DaggerTopologyComponent.builder();
  }

  public abstract TopologyTestDriver topologyTestDriver();

  @Component.Builder
  public interface Builder {
    @BindsInstance
    Builder configuration(KafkaConfiguration configuration);

    @BindsInstance
    Builder inputSerde(@App.InputSerde Serde<SensorState> serde);

    @BindsInstance
    Builder storeSerde(@App.StoreSerde Serde<SensorState> serde);

    @BindsInstance
    Builder resultSerde(@App.ResultSerde Serde<SensorStateDuration> serde);

    @BindsInstance
    Builder settings(Properties settings);

    TopologyComponent build();
  }
}
