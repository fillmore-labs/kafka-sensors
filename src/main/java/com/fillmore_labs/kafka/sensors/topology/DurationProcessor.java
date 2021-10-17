package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.logic.DurationCalculator;
import com.fillmore_labs.kafka.sensors.logic.DurationCalculatorFactory;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import javax.inject.Inject;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/* package */ final class DurationProcessor
    implements ValueTransformer<SensorState, SensorStateDuration> {
  public static final String SENSOR_STATES = "SensorStates";

  private final DurationCalculatorFactory calculatorFactory;

  private @MonotonicNonNull DurationCalculator calculator;

  @Inject
  /* package */ DurationProcessor(DurationCalculatorFactory calculatorFactory) {
    this.calculatorFactory = calculatorFactory;
  }

  @Override
  @EnsuresNonNull("calculator")
  public void init(ProcessorContext context) {
    var kvStore = StoreHelper.<String, SensorState>stateStore(context, SENSOR_STATES);
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
