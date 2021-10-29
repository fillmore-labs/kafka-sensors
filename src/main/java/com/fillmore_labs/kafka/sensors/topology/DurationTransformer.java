package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.logic.DurationProcessor;
import com.fillmore_labs.kafka.sensors.logic.DurationProcessorFactory;
import com.fillmore_labs.kafka.sensors.logic.LastEventStore;
import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
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

  private @MonotonicNonNull DurationProcessor processor;

  @AssistedInject
  /* package */ DurationTransformer(DurationProcessorFactory factory, @Assisted String storeName) {
    this.factory = factory;
    this.storeName = storeName;
  }

  @Override
  public void init(ProcessorContext context) {
    KeyValueStore<String, Event> store = context.getStateStore(storeName);
    processor = factory.create(new StoreAdapter(store));
  }

  @Override
  @SuppressWarnings({"nullness:override.return", "nullness:return"}) // Transformer is not annotated
  public @Nullable KeyValue<String, StateDuration> transform(
      @Nullable String key, @Nullable SensorState value) {
    assert processor != null : "@AssumeAssertion(nullness): init() not called";

    // A Kafka tombstone
    if (value == null) {
      if (key != null) {
        // delete the historic sensor position
        processor.delete(key);
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

    var result = processor.transform(id, value.getEvent());

    // Skip if no result (No historic data).
    if (result == null) {
      return null;
    }

    return KeyValue.pair(id, result);
  }

  @Override
  public void close() {
    assert processor != null : "@AssumeAssertion(nullness): init() not called";
    processor.close();
  }

  private static class StoreAdapter implements LastEventStore {
    private final KeyValueStore<String, Event> delegate;

    /* pacakge */ StoreAdapter(KeyValueStore<String, Event> delegate) {
      this.delegate = delegate;
    }

    @Override
    public @Nullable Event get(String id) {
      return delegate.get(id);
    }

    @Override
    public void put(String id, Event value) {
      delegate.put(id, value);
    }

    @Override
    public @Nullable Event delete(String id) {
      return delegate.delete(id);
    }
  }
}
