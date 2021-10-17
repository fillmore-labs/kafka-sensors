package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.rules.ExternalResource;

public final class CalculatorResource extends ExternalResource {
  private @MonotonicNonNull DurationCalculator calculator;

  @CanIgnoreReturnValue
  public @Nullable SensorStateDuration transform(@Nullable SensorState sensorState) {
    assert calculator != null : "@AssumeAssertion(nullness): before() not called";
    return calculator.transform(sensorState);
  }

  @Override
  protected void before() {
    var store = new TestKVStore();
    calculator = new DurationCalculator(store);
  }

  @Override
  protected void after() {
    assert calculator != null : "@AssumeAssertion(nullness): before() not called";
    try {
      calculator.close();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private static final class TestKVStore implements KVStore<String, SensorState> {
    private final Map<String, SensorState> map = new HashMap<>();

    @Override
    public @Nullable SensorState get(String key) {
      return map.get(key);
    }

    @Override
    public void put(String key, SensorState value) {
      map.put(key, value);
    }

    @Override
    public void close() {}
  }
}
