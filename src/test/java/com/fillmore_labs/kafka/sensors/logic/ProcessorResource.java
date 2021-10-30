package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.EventDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.junit.rules.ExternalResource;

public final class ProcessorResource extends ExternalResource {
  private @MonotonicNonNull MemoryEventStore eventStore;
  private @MonotonicNonNull DurationProcessor processor;

  @Override
  public void before() {
    eventStore = new MemoryEventStore();
    processor = new DurationProcessor(eventStore);
  }

  @CanIgnoreReturnValue
  public Optional<EventDuration> transform(Event event) {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    return processor.transform(event);
  }

  public void delete() {
    assert eventStore != null : "@AssumeAssertion(nullness): before() not called";
    eventStore.delete();
  }
}
