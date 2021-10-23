package com.fillmore_labs.kafka.sensors.topology;

import dagger.assisted.AssistedFactory;

@AssistedFactory
/* package */ interface DurationProcessorFactory {
  DurationProcessor create(String storeName);
}
