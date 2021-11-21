package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.logic.DurationProcessorFactory;
import com.fillmore_labs.kafka.sensors.logic.ReadingStore;
import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
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
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DurationTransformer
    implements Transformer<String, SensorState, KeyValue<String, StateDuration>> {
  private final DurationProcessorFactory factory;
  private final String storeName;

  private @MonotonicNonNull KeyValueStore<String, Reading> store;

  @AssistedInject
  /* package */ DurationTransformer(DurationProcessorFactory factory, @Assisted String storeName) {
    this.factory = factory;
    this.storeName = storeName;
  }

  private static KeyValue<String, StateDuration> mapReturnValue(String id, ReadingDuration result) {
    var stateDuration =
        StateDuration.builder()
            .id(id)
            .reading(result.getReading())
            .duration(result.getDuration())
            .build();
    return KeyValue.pair(id, stateDuration);
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
      return handleTombstone(key);
    }

    var id = value.getId();

    // Either we have no key or it should be our sensor id
    if (key != null && !id.equals(key)) {
      throw new StreamsException(String.format("Expected id %s, got %s", value.getId(), key));
    }

    var readingStore = new ReadingStoreAdapter(store, id);
    var processor = factory.create(readingStore);
    var transformed = processor.transform(value.getReading());

    // Skip if no result (No historic data), else return result.
    return transformed.map(result -> mapReturnValue(id, result)).orElse(null);
  }

  @Override
  public void close() {
    /* Nothing to do */
  }

  /**
   * Handle a Kafka tombstone (null value).
   *
   * @param key The key of the tombstone
   * @return A tombstone, or null
   * @see <a href="https://kafka.apache.org/documentation.html#design_compactionbasics">Log
   *     Compaction Basics</a>
   */
  @RequiresNonNull("store")
  private @Nullable KeyValue<String, StateDuration> handleTombstone(@Nullable String key) {
    if (key == null) {
      return null;
    }

    // delete the historic sensor position
    store.delete(key);

    @SuppressWarnings("nullness:argument") // KeyValue is not annotated
    var tombstone = KeyValue.<String, StateDuration>pair(key, null);

    // And return a Kafka tombstone
    return tombstone;
  }

  private static class ReadingStoreAdapter implements ReadingStore {
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
