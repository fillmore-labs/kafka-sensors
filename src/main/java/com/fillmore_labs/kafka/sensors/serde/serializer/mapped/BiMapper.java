package com.fillmore_labs.kafka.sensors.serde.serializer.mapped;

import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;

@Immutable
public interface BiMapper<S, T> {
  @PolyNull
  T map(@PolyNull S model);

  @PolyNull
  S unmap(@PolyNull T data);
}
