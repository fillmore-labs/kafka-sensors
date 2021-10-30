package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.EventDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.time.Duration;
import java.util.Optional;

public final class DurationProcessor {
  private final LastEventStore store;

  @AssistedInject
  /* package */ DurationProcessor(@Assisted LastEventStore store) {
    this.store = store;
  }

  @CanIgnoreReturnValue
  public Optional<EventDuration> transform(Event event) {
    // Get the historical position
    var storedState = store.get();

    // When we have no historical data, just store the current position
    if (storedState.isEmpty()) {
      store.put(event);
      return Optional.empty();
    }

    // We have historical data
    var oldState = storedState.get();

    // Update the position store if necessary.
    // We do not update for new events with the same position.
    if (oldState.getPosition() != event.getPosition()) {
      store.put(event);
    }

    var duration = Duration.between(oldState.getTime(), event.getTime());

    // Wrap the old position with a duration how log it lasted.
    return Optional.of(EventDuration.builder().event(oldState).duration(duration).build());
  }
}
