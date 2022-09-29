package com.fillmore_labs.kafka.sensors.logic;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.Reading.Position;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.errors.StreamsException;
import org.junit.Rule;
import org.junit.Test;

public final class ProcessorTest {
  private static final String KEY = "id";
  @Rule public final ProcessorResource processor = new ProcessorResource();

  @Test
  public void testNull() {
    processor.process(null, null);
    var forwarded = processor.forwarded();

    assertThat(forwarded).isEmpty();
  }

  @Test
  @SuppressWarnings("nullness:argument") // KeyValue is not annotated
  public void testTombstone() {
    processor.process(KEY, null);
    var forwarded = processor.forwarded();

    assertThat(forwarded).containsExactly(new KeyValue<String, StateDuration>(KEY, null));
  }

  @Test
  public void mapping() {
    var instant = Instant.ofEpochSecond(443_634_300L);
    var advancement = Duration.ofSeconds(30L);

    var initial = Reading.builder().time(instant).position(Position.OFF).build();
    var next = Reading.builder().time(instant.plus(advancement)).position(Position.ON).build();

    var state1 = SensorState.builder().id(KEY).reading(initial).build();
    var state2 = SensorState.builder().id(KEY).reading(next).build();

    processor.process(KEY, state1);
    processor.process(KEY, state2);

    var forwarded = processor.forwarded();

    var expected = StateDuration.builder().id(KEY).reading(initial).duration(advancement).build();
    assertThat(forwarded).containsExactly(new KeyValue<String, StateDuration>(KEY, expected));
  }

  @Test
  public void keyMismatch() {
    var instant = Instant.ofEpochSecond(443_634_300L);
    var initial = Reading.builder().time(instant).position(Position.OFF).build();
    var state1 = SensorState.builder().id(KEY).reading(initial).build();

    assertThrows(StreamsException.class, () -> processor.process(KEY + "1", state1));
  }
}
