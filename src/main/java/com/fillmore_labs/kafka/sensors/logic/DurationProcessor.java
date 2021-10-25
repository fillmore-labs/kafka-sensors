package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.time.Duration;
import org.apache.kafka.streams.errors.StreamsException;
import org.apache.kafka.streams.kstream.ValueTransformerWithKey;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class DurationProcessor
    implements ValueTransformerWithKey<String, SensorState, SensorStateDuration> {
  private final String storeName;

  private @MonotonicNonNull KeyValueStore<String, SensorState> store;

  @AssistedInject
  /* package */ DurationProcessor(@Assisted String storeName) {
    this.storeName = storeName;
  }

  @Override
  public void init(ProcessorContext context) {
    store = context.getStateStore(storeName);
  }

  @Override
  @CanIgnoreReturnValue
  @SuppressWarnings("nullness:override.return") // ValueTransformerWithKey is not annotated
  public @Nullable SensorStateDuration transform(String key, @Nullable SensorState value) {
    assert store != null : "@AssumeAssertion(nullness): init() not called";

    // A Kafka tombstone, delete the historic sensor state
    if (value == null) {
      store.delete(key);
      return null;
    }

    // The Sensor ID is our store key
    var sensorId = value.getId();

    if (!sensorId.equals(key)) {
      throw new StreamsException(String.format("Expected id %s, got %s", value.getId(), key));
    }

    // Get the historical state (might be null)
    var oldState = store.get(sensorId);

    // Update the state store if necessary.
    // Either we have no historical data, or the state has changed.
    // We do not update for new events with the same state.
    if (oldState == null || oldState.getState() != value.getState()) {
      store.put(sensorId, value);
    }

    // When we have no historical data, return null (a Kafka Tombstone).
    // https://kafka.apache.org/documentation.html#design_compactionbasics
    if (oldState == null) {
      return null;
    }

    var duration = Duration.between(oldState.getTime(), value.getTime());

    // Wrap the old state with a duration how log it lasted.
    return SensorStateDuration.builder().event(oldState).duration(duration).build();
  }

  @Override
  public void close() {}
}
