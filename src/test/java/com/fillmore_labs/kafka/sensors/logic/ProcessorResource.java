package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.util.List;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.api.InternalFixedKeyRecordFactory;
import org.apache.kafka.streams.processor.api.MockProcessorContext.CapturedForward;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.test.GenericInMemoryKeyValueStore;
import org.apache.kafka.test.MockInternalNewProcessorContext;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.rules.ExternalResource;

public final class ProcessorResource extends ExternalResource {
  public static final String STORE_NAME = "test-store";

  private @MonotonicNonNull MockInternalNewProcessorContext<String, StateDuration> context;
  private @MonotonicNonNull DurationProcessor processor;

  private static KeyValue<String, StateDuration> keyValue(
      CapturedForward<? extends String, ? extends StateDuration> capture) {
    return new KeyValue<>(capture.record().key(), capture.record().value());
  }

  @Override
  public void before() {
    context = new MockInternalNewProcessorContext<>();

    var stateStore = new GenericInMemoryKeyValueStore<String, ReadingDuration>(STORE_NAME);
    context.addStateStore(stateStore);

    processor = new DurationProcessor(STORE_NAME);
    processor.init(context);
  }

  @SuppressWarnings("nullness:argument") // Record is not annotated
  public void process(@Nullable String key, @Nullable SensorState value) {
    assert processor != null : "@AssumeAssertion(nullness): before() not called";
    var record = InternalFixedKeyRecordFactory.create(new Record<>(key, value, 0L));
    processor.process(record);
  }

  public List<KeyValue<String, StateDuration>> forwarded() {
    assert context != null : "@AssumeAssertion(nullness): before() not called";
    var forwarded = context.forwarded();
    context.resetForwards();
    return forwarded.stream().map(ProcessorResource::keyValue).toList();
  }
}
