package com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes;

import static com.google.common.truth.Truth.assertThat;

import java.time.Instant;
import org.junit.Test;

public final class InstantNanosHelperTest {
  private static final long NANOS_PER_SECOND = 1_000_000_000L;
  private static final long EPOCH_SECOND = -9_223_372_037L;
  private static final long NANO_ADJUSTMENT = 145_224_192L;

  @Test
  public void instant2Nanos() {
    var instant = Instant.ofEpochSecond(EPOCH_SECOND, NANO_ADJUSTMENT);
    assertThat(InstantNanosHelper.instant2Nanos(instant))
        .isEqualTo((EPOCH_SECOND + 1) * NANOS_PER_SECOND - (NANOS_PER_SECOND - NANO_ADJUSTMENT));
  }
}
