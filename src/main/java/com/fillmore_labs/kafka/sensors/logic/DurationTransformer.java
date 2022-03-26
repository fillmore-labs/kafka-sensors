package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
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
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DurationTransformer
    implements Transformer<String, SensorState, KeyValue<String, StateDuration>> {
  private final String storeName;

  private @MonotonicNonNull DurationProcessor durationProcessor;

  @AssistedInject
  /* package */ DurationTransformer(@Assisted String storeName) {
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
    var stateStore = context.<KeyValueStore<String, Reading>>getStateStore(storeName);
    durationProcessor = new DurationProcessor(stateStore);
  }

  @Override
  @SuppressWarnings({"nullness:override.return", "nullness:return"}) // Transformer is not annotated
  public @Nullable KeyValue<String, StateDuration> transform(
      @Nullable String key, @Nullable SensorState value) {
    assert durationProcessor != null : "@AssumeAssertion(nullness): init() not called";

    // A Kafka tombstone
    if (value == null) {
      return handleTombstone(key);
    }

    // Either we have no key or it should be our sensor id
    var id = value.getId();
    if (!(key == null || id.equals(key))) {
      throw new StreamsException(String.format("Expected id %s, got %s", value.getId(), key));
    }

    var reading = value.getReading();
    var transformed = durationProcessor.transform(id, reading);

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
   * @param id The key of the tombstone
   * @return A tombstone, or null
   * @see <a href="https://kafka.apache.org/documentation.html#design_compactionbasics">Log
   *     Compaction Basics</a>
   */
  @RequiresNonNull("durationProcessor")
  private @Nullable KeyValue<String, StateDuration> handleTombstone(@Nullable String id) {
    if (id == null) {
      return null;
    }

    // delete the historic sensor position
    durationProcessor.delete(id);

    @SuppressWarnings("nullness:argument") // KeyValue is not annotated
    var tombstone = KeyValue.<String, StateDuration>pair(id, null);

    // And return a Kafka tombstone
    return tombstone;
  }
}
