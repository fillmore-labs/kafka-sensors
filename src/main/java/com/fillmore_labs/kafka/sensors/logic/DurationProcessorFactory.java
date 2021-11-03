package com.fillmore_labs.kafka.sensors.logic;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface DurationProcessorFactory {
  DurationProcessor create(ReadingStore store);
}
