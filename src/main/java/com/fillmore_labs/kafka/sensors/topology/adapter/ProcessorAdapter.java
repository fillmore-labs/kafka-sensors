package com.fillmore_labs.kafka.sensors.topology.adapter;

import com.fillmore_labs.kafka.sensors.logic.DurationProcessorFactory;
import com.fillmore_labs.kafka.sensors.logic.ReadingStore;
import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.util.Optional;
import org.apache.kafka.streams.state.KeyValueStore;

/* package */ final class ProcessorAdapter {
  private final DurationProcessorFactory factory;
  private final KeyValueStore<String, Reading> store;

  @AssistedInject
  /* package */ ProcessorAdapter(
      DurationProcessorFactory factory, @Assisted KeyValueStore<String, Reading> store) {
    this.factory = factory;
    this.store = store;
  }

  public Optional<ReadingDuration> transform(String id, Reading reading) {
    var readingStore = new ReadingStoreAdapter(store, id);
    var processor = factory.create(readingStore);
    return processor.transform(reading);
  }

  public void delete(String id) {
    store.delete(id);
  }

  private static final class ReadingStoreAdapter implements ReadingStore {
    private final KeyValueStore<String, Reading> delegate;
    private final String id;

    /* package */ ReadingStoreAdapter(KeyValueStore<String, Reading> delegate, String id) {
      this.delegate = delegate;
      this.id = id;
    }

    @Override
    public Optional<Reading> latest() {
      return Optional.ofNullable(delegate.get(id));
    }

    @Override
    public void add(Reading value) {
      delegate.put(id, value);
    }
  }
}
