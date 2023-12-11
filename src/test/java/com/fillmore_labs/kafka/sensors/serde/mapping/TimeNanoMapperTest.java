package com.fillmore_labs.kafka.sensors.serde.mapping;

import static com.google.common.truth.Truth.assertThat;

import java.time.Instant;
import org.junit.Test;

public final class TimeNanoMapperTest {
  @Test
  public void mapTime() {
    var time = Instant.parse("1960-01-01T12:34:56.789012345Z");
    var nanoseconds = TimeNanoMapper.mapTime(time);
    assertThat(nanoseconds).isLessThan(0L);
    var instant = TimeNanoMapper.mapTime(nanoseconds);
    assertThat(instant).isEqualTo(time);
  }

  @Test
  public void epoch() {
    var instant = TimeNanoMapper.mapTime(0L);
    assertThat(instant).isEqualTo(Instant.EPOCH);
  }
}
