package com.fillmore_labs.kafka.sensors.serde.serializer.mapped;

import static org.mockito.BDDMockito.then;

import java.util.Map;
import java.util.function.Function;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public final class MappedSerializerTest {
  @Mock
  @SuppressWarnings("nullness:initialization.field.uninitialized")
  public Serializer<?> serializer;

  private @MonotonicNonNull Serializer<?> mappedSerializer;

  @Before
  @EnsuresNonNull("mappedSerializer")
  public void before() {
    mappedSerializer = new MappedSerializer<>(serializer, Function.identity());
  }

  @Test
  @RequiresNonNull("mappedSerializer")
  public void configure() {
    mappedSerializer.configure(Map.of(), /* isKey= */ false);

    then(serializer).should().configure(Map.of(), /* isKey= */ false);
  }

  @Test
  @RequiresNonNull("mappedSerializer")
  public void close() {
    mappedSerializer.close();

    then(serializer).should().close();
  }
}
