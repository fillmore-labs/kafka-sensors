package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.rules.ExternalResource;

public final class ProcessorResource extends ExternalResource {
  private @MonotonicNonNull DurationProcessor processor;

  @Override
  public void before() {
    processor = new DurationProcessor(new MemoryEventStore());
  }

  @Override
  public void after() {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    processor.close();
  }

  @CanIgnoreReturnValue
  public @Nullable StateDuration transform(SensorState sensorState) {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    return processor.transform(sensorState.getId(), sensorState.getEvent());
  }

  public void delete(String id) {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    processor.delete(id);
  }
}
