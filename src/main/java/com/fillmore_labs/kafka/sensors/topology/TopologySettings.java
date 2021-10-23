package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import org.apache.kafka.common.serialization.Serde;
import org.immutables.value.Value;

@Value.Immutable
public interface TopologySettings {
  static ImmutableTopologySettings.Builder builder() {
    return ImmutableTopologySettings.builder();
  }

  Serde<SensorState> inputSerde();

  String inputTopic();

  Serde<SensorState> storeSerde();

  String storeName();

  Serde<SensorStateDuration> resultSerde();

  String resultTopic();
}
