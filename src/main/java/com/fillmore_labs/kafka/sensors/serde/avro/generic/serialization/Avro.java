package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

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
