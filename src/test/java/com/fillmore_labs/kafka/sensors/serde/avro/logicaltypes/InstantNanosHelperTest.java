package com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes;

import static com.google.common.truth.Truth.assertThat;

import java.time.Instant;
import org.junit.Test;

public final class InstantNanosHelperTest {
  private static final long NANOS_PER_SECOND = 1_000_000_000L;

  @Test
  public void instant2Nanos() {
    var instant = Instant.ofEpochSecond(-1L, 1L);
    assertThat(InstantNanosHelper.instant2Nanos(instant)).isEqualTo(-NANOS_PER_SECOND + 1L);
  }
}
