package com.fillmore_labs.kafka.sensors.logic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.ReadingDuration;
import java.time.Duration;
import java.util.Optional;
import org.apache.kafka.streams.state.KeyValueStore;

/* package */ final class DurationProcessor {
  private final KeyValueStore<String, Reading> stateStore;

  /* package */ DurationProcessor(KeyValueStore<String, Reading> stateStore) {
    this.stateStore = stateStore;
  }

  public Optional<ReadingDuration> transform(String id, Reading reading) {
    // Get the historical position
    var oldState = stateStore.get(id);

    // When we have no historical data, just store the current position
    if (oldState == null) {
      stateStore.put(id, reading);
      return Optional.empty();
    }

    // Update the position store if necessary.
    // We do not update for new readings with the same position.
    if (oldState.getPosition() != reading.getPosition()) {
      stateStore.put(id, reading);
    }

    var duration = Duration.between(oldState.getTime(), reading.getTime());

    // Wrap the old position with a duration how log it lasted.
    return Optional.of(ReadingDuration.builder().reading(oldState).duration(duration).build());
  }

  public void delete(String id) {
    stateStore.delete(id);
  }
}
