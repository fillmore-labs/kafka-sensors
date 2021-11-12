package com.fillmore_labs.kafka.sensors.serde.mapping;

import java.time.Duration;
import java.time.Instant;

public final class TimeNanoMapper {
  private static final long NANOS_PER_SECOND = 1_000_000_000L;

  private TimeNanoMapper() {}

  public static Instant mapTime(long nanosFromEpoch) {
    var epochSecond = nanosFromEpoch / NANOS_PER_SECOND;
    var nanoAdjustment = nanosFromEpoch % NANOS_PER_SECOND;

    return Instant.ofEpochSecond(epochSecond, nanoAdjustment);
  }

  public static long mapTime(Instant instant) {
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

  public static Duration mapDuration(long nanos) {
    return Duration.ofNanos(nanos);
  }

  public static long mapDuration(Duration duration) {
    return duration.toNanos();
  }
}
