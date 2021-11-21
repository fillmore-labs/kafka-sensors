package com.fillmore_labs.kafka.sensors.serde.serializer.mapped;

import static org.mockito.Mockito.verify;

import java.util.Map;
import java.util.function.Function;
import org.apache.kafka.common.serialization.Deserializer;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public final class MappedDeserializerTest {
  @Mock
  @SuppressWarnings("nullness:initialization.field.uninitialized")
  public Deserializer<?> deserializer;

  private @MonotonicNonNull Deserializer<?> mappedDeserializer;

  @Before
  @EnsuresNonNull("mappedDeserializer")
  public void before() {
    mappedDeserializer = new MappedDeserializer<>(deserializer, Function.identity());
  }

  @Test
  @RequiresNonNull("mappedDeserializer")
  public void configure() {
    mappedDeserializer.configure(Map.of(), /* isKey= */ false);

    verify(deserializer).configure(Map.of(), /* isKey= */ false);
  }

  @Test
  @RequiresNonNull("mappedDeserializer")
  public void close() {
    mappedDeserializer.close();

    verify(deserializer).close();
  }
}
