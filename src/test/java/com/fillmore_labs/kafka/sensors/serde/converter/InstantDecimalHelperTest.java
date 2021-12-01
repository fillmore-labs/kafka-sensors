package com.fillmore_labs.kafka.sensors.serde.converter;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.time.Instant;
import org.junit.Test;

public class InstantDecimalHelperTest {
  @Test
  public void decimal2Instant() {
    var decimal = BigDecimal.ZERO;

    assertThat(InstantDecimalHelper.decimal2Instant(decimal)).isEqualTo(Instant.EPOCH);
  }

  @Test
  public void testOutOfRange() {
    var decimal = BigDecimal.valueOf(Instant.MAX.getEpochSecond()).add(BigDecimal.ONE);

    assertThrows(
        IllegalArgumentException.class, () -> InstantDecimalHelper.decimal2Instant(decimal));
  }
}
