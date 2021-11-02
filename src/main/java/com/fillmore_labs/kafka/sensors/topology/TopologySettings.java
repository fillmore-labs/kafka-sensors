package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import org.apache.kafka.common.serialization.Serde;
import org.immutables.value.Value;

@Value.Immutable
public interface TopologySettings {
  static ImmutableTopologySettings.Builder builder() {
    return ImmutableTopologySettings.builder();
  }

  Serde<SensorState> inputSerde();

  String inputTopic();

  Serde<Reading> storeSerde();

  String storeName();

  Serde<StateDuration> resultSerde();

  String resultTopic();
}
