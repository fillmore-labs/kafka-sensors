package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.io.Closeable;
import java.time.Duration;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class DurationProcessor implements Closeable {
  private final LastEventStore store;

  @AssistedInject
  /* package */ DurationProcessor(@Assisted LastEventStore store) {
    this.store = store;
  }

  public void delete(String id) {
    store.delete(id);
  }

  @CanIgnoreReturnValue
  public @Nullable StateDuration transform(String id, Event event) {
    // Get the historical position (might be null)
    var oldState = store.get(id);

    // When we have no historical data, just store the position
    if (oldState == null) {
      store.put(id, event);
      return null;
    }

    // Update the position store if necessary.
    // We do not update for new events with the same position.
    if (oldState.getPosition() != event.getPosition()) {
      store.put(id, event);
    }

    var duration = Duration.between(oldState.getTime(), event.getTime());

    // Wrap the old position with a duration how log it lasted.
    return StateDuration.builder().id(id).event(oldState).duration(duration).build();
  }

  @Override
  public void close() {}
}
