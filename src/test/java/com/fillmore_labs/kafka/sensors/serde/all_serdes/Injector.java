package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import org.checkerframework.checker.initialization.qual.UnknownInitialization;

@FunctionalInterface
public interface Injector<T> {
  void injectMembers(@UnknownInitialization T t);
}
