package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface DurationCalculatorFactory {
  DurationCalculator create(KVStore<String, SensorState> store);
}
