package com.fillmore_labs.kafka.sensors.topology.adapter;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface DurationTransformerFactory {
  DurationTransformer create(String storeName);
}
