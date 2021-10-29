package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface LastEventStore {
  @Nullable Event get(String id);

  void put(String id, Event value);

  @CanIgnoreReturnValue
  @Nullable Event delete(String id);
}
