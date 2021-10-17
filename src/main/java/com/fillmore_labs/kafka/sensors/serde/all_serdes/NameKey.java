package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.fillmore_labs.kafka.sensors.serde.common.Name;
import dagger.MapKey;
import java.lang.annotation.Retention;

@MapKey
@Retention(RUNTIME)
public @interface NameKey {
  Name value();
}
