package com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes;

import java.time.Instant;

public final class InstantNanosHelper {
  private static final long NANOS_PER_SECOND = 1_000_000_000L;

  private InstantNanosHelper() {}

  public static Instant nanos2Instant(long nanosFromEpoch) {
    var epochSecond = nanosFromEpoch / NANOS_PER_SECOND;
    var nanoAdjustment = nanosFromEpoch % NANOS_PER_SECOND;

    return Instant.ofEpochSecond(epochSecond, nanoAdjustment);
  }

  public static Long instant2Nanos(Instant instant) {
    var seconds = instant.getEpochSecond();
    var nanos = instant.getNano();

    if (seconds < 0 && nanos > 0) {
      var result = Math.multiplyExact(seconds + 1, NANOS_PER_SECOND);
      var adjustment = nanos - NANOS_PER_SECOND;

      return Math.addExact(result, adjustment);
    } else {
      var result = Math.multiplyExact(seconds, NANOS_PER_SECOND);

      return Math.addExact(result, nanos);
    }
  }
}
