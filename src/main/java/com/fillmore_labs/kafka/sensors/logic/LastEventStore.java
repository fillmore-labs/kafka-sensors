package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Event;
import java.util.Optional;

public interface LastEventStore {
  Optional<Event> get();

  void put(Event value);
}
