package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

public @interface Avro {
  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface Reading {}

  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface SensorState {}

  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface StateDuration {}
}
