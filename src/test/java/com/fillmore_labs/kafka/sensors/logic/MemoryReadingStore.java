package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class MemoryReadingStore implements LastReadingStore {
  private @Nullable Reading reading;

  @Override
  public Optional<Reading> get() {
    return Optional.ofNullable(reading);
  }

  @Override
  public void put(Reading value) {
    reading = value;
  }

  public void delete() {
    reading = null;
  }
}
