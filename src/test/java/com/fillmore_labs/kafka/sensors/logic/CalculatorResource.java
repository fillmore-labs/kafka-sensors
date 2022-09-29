package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.junit.rules.ExternalResource;

public final class CalculatorResource extends ExternalResource {
  public static final String STORE_NAME = "test-store";
  public static final String ID = "id";

  private @MonotonicNonNull DurationCalculator calculator;

  @Override
  public void before() {
    var readingStore = new MapKeyValueStore<String, Reading>(STORE_NAME);
    calculator = new DurationCalculator(readingStore);
  }

  @CanIgnoreReturnValue
  public Optional<ReadingDuration> compute(Reading reading) {
    assert calculator != null : "@AssumeAssertion(nullness): before() not called";
    return calculator.compute(ID, reading);
  }

  public void delete() {
    assert calculator != null : "@AssumeAssertion(nullness): before() not called";
    calculator.delete(ID);
  }
}
