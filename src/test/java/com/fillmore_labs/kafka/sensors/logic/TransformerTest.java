package com.fillmore_labs.kafka.sensors.logic;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.Reading.Position;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.time.Duration;
import java.time.Instant;
import org.apache.kafka.streams.errors.StreamsException;
import org.junit.Rule;
import org.junit.Test;

public final class TransformerTest {

  private static final String KEY = "id";
  @Rule public final TransformerResource transformer = new TransformerResource();

  @Test
  public void testNull() {
    var result = transformer.transform(null, null);

    assertThat(result).isNull();
  }

  @Test
  @SuppressWarnings("nullness:dereference.of.nullable") // We are testing for this
  public void testTombstone() {
    var result = transformer.transform(KEY, null);

    assertThat(result).isNotNull();
    assertThat(result.key).isEqualTo(KEY);
    assertThat(result.value).isNull();
  }

  @Test
  @SuppressWarnings("nullness:dereference.of.nullable") // We are testing for this
  public void mapping() {
    var instant = Instant.ofEpochSecond(443_634_300L);
    var advancement = Duration.ofSeconds(30L);

    var initial = Reading.builder().time(instant).position(Position.OFF).build();
    var next = Reading.builder().time(instant.plus(advancement)).position(Position.ON).build();

    var state1 = SensorState.builder().id(KEY).reading(initial).build();
    var state2 = SensorState.builder().id(KEY).reading(next).build();

    var expected = StateDuration.builder().id(KEY).reading(initial).duration(advancement).build();
    var result1 = transformer.transform(KEY, state1);
    var result2 = transformer.transform(KEY, state2);

    assertThat(result1).isNull();
    assertThat(result2).isNotNull();
    assertThat(result2.key).isEqualTo(KEY);
    assertThat(result2.value).isEqualTo(expected);
  }

  @Test
  public void keyMismatch() {
    var instant = Instant.ofEpochSecond(443_634_300L);
    var initial = Reading.builder().time(instant).position(Position.OFF).build();
    var state1 = SensorState.builder().id(KEY).reading(initial).build();

    assertThrows(StreamsException.class, () -> transformer.transform(KEY + "1", state1));
  }
}
