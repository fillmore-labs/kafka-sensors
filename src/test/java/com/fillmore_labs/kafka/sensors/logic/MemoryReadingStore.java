package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class MemoryReadingStore implements ReadingStore {
  private @Nullable Reading reading;

  @Override
  public Optional<Reading> latest() {
    return Optional.ofNullable(reading);
  }

  @Override
  public void add(Reading value) {
    reading = value;
  }

  public void clear() {
    reading = null;
  }
}
