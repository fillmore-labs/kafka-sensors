package com.fillmore_labs.kafka.sensors.serde.confluent.common;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Confluent {}
