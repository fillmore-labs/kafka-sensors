package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.junit.rules.ExternalResource;

public final class ProcessorResource extends ExternalResource {
  public static final String STORE_NAME = "test-store";
  public static final String ID = "id";

  private @MonotonicNonNull DurationProcessor processor;

  @Override
  public void before() {
    var readingStore = new MapKeyValueStore<String, Reading>(STORE_NAME);
    processor = new DurationProcessor(readingStore);
  }

  @CanIgnoreReturnValue
  public Optional<ReadingDuration> transform(Reading reading) {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    return processor.transform(ID, reading);
  }

  public void delete() {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    processor.delete(ID);
  }
}
