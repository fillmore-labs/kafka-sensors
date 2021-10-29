package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Event;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class MemoryEventStore implements LastEventStore {
  private final Map<String, Event> map = new HashMap<>();

  @Override
  public @Nullable Event get(String id) {
    return map.get(id);
  }

  @Override
  public void put(String id, Event value) {
    map.put(id, value);
  }

  @Override
  public @Nullable Event delete(String id) {
    return map.remove(id);
  }
}
