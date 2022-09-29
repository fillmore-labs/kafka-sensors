package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import org.apache.kafka.streams.errors.StreamsException;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueStore;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DurationProcessor
    implements Processor<String, SensorState, String, StateDuration> {
  private final String storeName;

  private @MonotonicNonNull ProcessorContext<String, StateDuration> context;
  private @MonotonicNonNull DurationCalculator durationCalculator;

  @AssistedInject
  /* package */ DurationProcessor(@Assisted String storeName) {
    this.storeName = storeName;
  }

  @Override
  public void init(ProcessorContext<String, StateDuration> ctxt) {
    context = ctxt;
    var stateStore = ctxt.<KeyValueStore<String, Reading>>getStateStore(storeName);
    durationCalculator = new DurationCalculator(stateStore);
  }

  @Override
  public void process(Record<String, SensorState> record) {
    assert context != null : "@AssumeAssertion(nullness): init() not called";
    assert durationCalculator != null : "@AssumeAssertion(nullness): init() not called";

    var value = record.value();

    // A Kafka tombstone
    if (value == null) {
      handleTombstone(record);
      return;
    }

    var id = value.getId();

    // Either we have no key or it should be our sensor id
    var key = record.key();
    if (!(key == null || id.equals(key))) {
      throw new StreamsException(String.format("Expected id %s, got %s", value.getId(), key));
    }

    var reading = value.getReading();
    var transformed = durationCalculator.compute(id, reading);

    // Skip if no result (No historic data), else forward result.
    if (transformed.isEmpty()) {
      return;
    }

    var newValue = transformed.get();
    var stateDuration =
        StateDuration.builder()
            .id(id)
            .reading(newValue.getReading())
            .duration(newValue.getDuration())
            .build();
    var result = new Record<>(id, stateDuration, record.timestamp(), record.headers());
    context.forward(result);
  }

  @Override
  public void close() {
    /* Nothing to do */
  }

  @RequiresNonNull({"context", "durationCalculator"})
  private void handleTombstone(Record<String, SensorState> record) {
    var key = record.key();
    if (key == null) {
      return;
    }

    // delete the historic sensor position
    durationCalculator.delete(key);

    @SuppressWarnings("nullness:argument") // KeyValue is not annotated
    var tombstone =
        new Record<String, StateDuration>(key, null, record.timestamp(), record.headers());

    // And forward a Kafka tombstone
    context.forward(tombstone);
  }
}
