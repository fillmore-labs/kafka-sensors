package com.fillmore_labs.kafka.sensors.serde.converter;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.time.Duration;
import org.junit.Test;

public final class DurationDecimalHelperTest {
  @Test
  public void decimal2Duration() {
    var decimal = BigDecimal.ZERO;

    assertThat(DurationDecimalHelper.decimal2Duration(decimal)).isEqualTo(Duration.ZERO);
  }

  @Test
  public void outOfRange() {
    var decimal = BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.ONE);

    assertThrows(
        IllegalArgumentException.class, () -> DurationDecimalHelper.decimal2Duration(decimal));
  }
}
