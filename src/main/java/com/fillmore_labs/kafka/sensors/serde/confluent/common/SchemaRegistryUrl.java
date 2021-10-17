package com.fillmore_labs.kafka.sensors.serde.confluent.common;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface SchemaRegistryUrl {}
