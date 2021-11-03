package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.time.Duration;
import java.util.Optional;

public final class DurationProcessor {
  private final ReadingStore store;

  @AssistedInject
  /* package */ DurationProcessor(@Assisted ReadingStore store) {
    this.store = store;
  }

  @CanIgnoreReturnValue
  public Optional<ReadingDuration> transform(Reading reading) {
    // Get the historical position
    var storedState = store.latest();

    // When we have no historical data, just store the current position
    if (storedState.isEmpty()) {
      store.add(reading);
      return Optional.empty();
    }

    // We have historical data
    var oldState = storedState.get();

    // Update the position store if necessary.
    // We do not update for new readings with the same position.
    if (oldState.getPosition() != reading.getPosition()) {
      store.add(reading);
    }

    var duration = Duration.between(oldState.getTime(), reading.getTime());

    // Wrap the old position with a duration how log it lasted.
    return Optional.of(ReadingDuration.builder().reading(oldState).duration(duration).build());
  }
}
