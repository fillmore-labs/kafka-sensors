package com.fillmore_labs.kafka.sensors.topology.adapter;

import com.fillmore_labs.kafka.sensors.model.Reading;
import dagger.assisted.AssistedFactory;
import org.apache.kafka.streams.state.KeyValueStore;

@AssistedFactory
/* package */ interface ProcessorAdapterFactory {
  ProcessorAdapter create(KeyValueStore<String, Reading> store);
}
