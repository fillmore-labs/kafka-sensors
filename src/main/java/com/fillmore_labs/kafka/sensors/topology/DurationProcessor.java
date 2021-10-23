package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.logic.DurationCalculator;
import com.fillmore_labs.kafka.sensors.logic.DurationCalculatorFactory;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/* package */ final class DurationProcessor
    implements ValueTransformer<SensorState, SensorStateDuration> {
  private final DurationCalculatorFactory calculatorFactory;
  private final String storeName;

  private @MonotonicNonNull DurationCalculator calculator;

  @AssistedInject
  /* package */ DurationProcessor(
      DurationCalculatorFactory calculatorFactory, @Assisted String storeName) {
    this.calculatorFactory = calculatorFactory;
    this.storeName = storeName;
  }

  @Override
  @EnsuresNonNull("calculator")
  public void init(ProcessorContext context) {
    var kvStore = StoreHelper.<String, SensorState>stateStoreFromContext(context, storeName);
    this.calculator = calculatorFactory.create(StoreHelper.mapStore(kvStore));
  }

  @Override
  @SuppressWarnings("nullness:override.return") // ValueTransformer is not annotated
  public @Nullable SensorStateDuration transform(@Nullable SensorState sensorState) {
    // init has to be called first
    assert calculator != null : "@AssumeAssertion(nullness): init not called";

    return calculator.transform(sensorState);
  }

  @Override
  public void close() {}
}
