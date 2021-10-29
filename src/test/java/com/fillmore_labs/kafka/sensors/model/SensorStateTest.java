package com.fillmore_labs.kafka.sensors.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.model.Event.Position;
import java.time.Duration;
import java.time.Instant;
import org.junit.Test;

public final class SensorStateTest {
  @Test
  public void creation() {
    var time = Instant.ofEpochSecond(443634300L);

    var sensorState =
        SensorState.builder()
            .id("7331")
            .event(Event.builder().time(time).position(Position.ON).build())
            .build();

    assertThat(sensorState.getId()).isEqualTo("7331");
    assertThat(sensorState.getEvent().getTime()).isEqualTo(time);
    assertThat(sensorState.getEvent().getPosition()).isEqualTo(Position.ON);
  }

  @Test
  public void required() {
    assertThrows(IllegalStateException.class, () -> SensorState.builder().build());
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(NullPointerException.class, () -> SensorState.builder().id(null).build());
  }

  @Test
  public void durationShouldNotBeNegative() {
    var instant = Instant.ofEpochSecond(443634300L);

    var event = Event.builder().time(instant).position(Position.ON).build();

    assertThrows(
        IllegalStateException.class,
        () ->
            StateDuration.builder()
                .id("7331")
                .event(event)
                .duration(Duration.ofMillis(-1L))
                .build());
  }
}
