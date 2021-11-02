package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import java.util.Optional;

public interface LastReadingStore {
  Optional<Reading> get();

  void put(Reading value);
}
