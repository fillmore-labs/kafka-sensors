package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.logic.DurationProcessorFactory;
import com.fillmore_labs.kafka.sensors.logic.LastEventStore;
import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.util.Optional;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.errors.StreamsException;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class DurationTransformer
    implements Transformer<String, SensorState, KeyValue<String, StateDuration>> {
  private final DurationProcessorFactory factory;
  private final String storeName;

  private @MonotonicNonNull KeyValueStore<String, Event> store;

  @AssistedInject
  /* package */ DurationTransformer(DurationProcessorFactory factory, @Assisted String storeName) {
    this.factory = factory;
    this.storeName = storeName;
  }

  @Override
  public void init(ProcessorContext context) {
    store = context.getStateStore(storeName);
  }

  @Override
  @SuppressWarnings({"nullness:override.return", "nullness:return"}) // Transformer is not annotated
  public @Nullable KeyValue<String, StateDuration> transform(
      @Nullable String key, @Nullable SensorState value) {
    assert store != null : "@AssumeAssertion(nullness): init() not called";

    // A Kafka tombstone
    if (value == null) {
      if (key != null) {
        var eventStore = new StoreAdapter(store, key);
        // delete the historic sensor position
        eventStore.delete();
        // And return a Kafka tombstone
        // https://kafka.apache.org/documentation.html#design_compactionbasics
        return KeyValue.pair(key, null);
      }
      return null;
    }

    var id = value.getId();

    // Either we have no key or it is our sensor id
    if (key != null && !id.equals(key)) {
      throw new StreamsException(String.format("Expected id %s, got %s", value.getId(), key));
    }

    var eventStore = new StoreAdapter(store, id);
    var processor = factory.create(eventStore);
    var transformed = processor.transform(value.getEvent());

    // Skip if no result (No historic data).
    if (transformed.isEmpty()) {
      return null;
    }

    var result = transformed.get();

    var stateDuration =
        StateDuration.builder()
            .id(id)
            .event(result.getEvent())
            .duration(result.getDuration())
            .build();
    return KeyValue.pair(id, stateDuration);
  }

  @Override
  public void close() {}

  private static class StoreAdapter implements LastEventStore {
    private final KeyValueStore<String, Event> delegate;
    private final String id;

    /* pacakge */ StoreAdapter(KeyValueStore<String, Event> delegate, String id) {
      this.delegate = delegate;
      this.id = id;
    }

    @Override
    public Optional<Event> get() {
      return Optional.ofNullable(delegate.get(id));
    }

    @Override
    public void put(Event value) {
      delegate.put(id, value);
    }

    @Override
    public void delete() {
      delegate.delete(id);
    }
  }
}
