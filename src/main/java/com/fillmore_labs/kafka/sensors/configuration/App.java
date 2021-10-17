package com.fillmore_labs.kafka.sensors.configuration;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

public @interface App {
  @Target({METHOD, PARAMETER, FIELD})
  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface InputSerde {}

  @Target({METHOD, PARAMETER, FIELD})
  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface StoreSerde {}

  @Target({METHOD, PARAMETER, FIELD})
  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface ResultSerde {}
}
