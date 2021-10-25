package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.kafka.streams.processor.MockProcessorContext;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.rules.ExternalResource;

public final class ProcessorResource extends ExternalResource {
  private static final String STORE_NAME = "test-store";

  private @MonotonicNonNull DurationProcessor processor;

  @CanIgnoreReturnValue
  public @Nullable SensorStateDuration transform(SensorState sensorState) {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    return processor.transform(sensorState.getId(), sensorState);
  }

  @CanIgnoreReturnValue
  public @Nullable SensorStateDuration tombstone(String id) {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    return processor.transform(id, null);
  }

  @Override
  protected void before() {
    var context = new MockProcessorContext();

    var store = new MapKeyValueStore<String, SensorState>(STORE_NAME);
    context.register(store, (byte[] key, byte[] value) -> {});

    processor = new DurationProcessor(STORE_NAME);
    processor.init(context);
  }

  @Override
  protected void after() {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    processor.close();
  }
}
