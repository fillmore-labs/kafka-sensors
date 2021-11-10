package com.fillmore_labs.kafka.sensors.topology.context;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.io.Closeable;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;

public final class SingleTestResource implements Closeable {
  private final SingleTestComponent singleTestComponent;

  public SingleTestResource(TestResource testResource, Formats formats) {
    this.singleTestComponent = testResource.singleTestComponentBuilder().formats(formats).build();
  }

  public TestInputTopic<String, SensorState> inputTopic() {
    return singleTestComponent.inputTopic();
  }

  public TestOutputTopic<String, StateDuration> resultTopic() {
    return singleTestComponent.resultTopic();
  }

  @Override
  public void close() {
    singleTestComponent.testDriver().close();
  }
}
