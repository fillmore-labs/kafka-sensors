package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.MockProcessorContext;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.rules.ExternalResource;

public final class TransformerResource extends ExternalResource {
  public static final String STORE_NAME = "test-store";

  private @MonotonicNonNull DurationTransformer transformer;

  @Override
  @SuppressWarnings("nullness:argument") // MockProcessorContext is not annotated
  public void before() {
    var context = new MockProcessorContext();

    var stateStore = new MapKeyValueStore<String, ReadingDuration>(STORE_NAME);
    context.register(stateStore, null);

    transformer = new DurationTransformer(STORE_NAME);
    transformer.init(context);
  }

  public @Nullable KeyValue<String, StateDuration> transform(
      @Nullable String key, @Nullable SensorState value) {
    assert transformer != null : "@AssumeAssertion(nullness): before() not called";
    return transformer.transform(key, value);
  }
}
