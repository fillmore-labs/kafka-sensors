package com.fillmore_labs.kafka.sensors.logic;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface DurationTransformerFactory {
  DurationTransformer create(String storeName);
}
