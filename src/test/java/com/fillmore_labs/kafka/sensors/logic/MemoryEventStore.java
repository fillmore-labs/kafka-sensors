package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Event;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class MemoryEventStore implements LastEventStore {
  private @Nullable Event event;

  @Override
  public Optional<Event> get() {
    return Optional.ofNullable(event);
  }

  @Override
  public void put(Event value) {
    event = value;
  }

  @Override
  public void delete() {
    event = null;
  }
}
