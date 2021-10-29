package com.fillmore_labs.kafka.sensors.topology.context;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.topology.TopologyModule;
import dagger.BindsInstance;
import dagger.Subcomponent;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.TopologyTestDriver;

@SingleTestModule.TestRun
@Subcomponent(modules = {SingleTestModule.class, TopologyModule.class})
public interface SingleTestComponent {
  TopologyTestDriver testDriver();

  TestInputTopic<String, SensorState> inputTopic();

  TestOutputTopic<String, StateDuration> resultTopic();

  @Subcomponent.Builder
  interface Builder {
    @BindsInstance
    Builder formats(Formats formats);

    SingleTestComponent build();
  }
}
