package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.junit.rules.ExternalResource;

public final class ProcessorResource extends ExternalResource {
  private @MonotonicNonNull MemoryReadingStore readingStore;
  private @MonotonicNonNull DurationProcessor processor;

  @Override
  public void before() {
    readingStore = new MemoryReadingStore();
    processor = new DurationProcessor(readingStore);
  }

  @CanIgnoreReturnValue
  public Optional<ReadingDuration> transform(Reading reading) {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    return processor.transform(reading);
  }

  public void delete() {
    assert readingStore != null : "@AssumeAssertion(nullness): before() not called";
    readingStore.clear();
  }
}
