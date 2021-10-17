package com.fillmore_labs.kafka.sensors.serde.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.FluentLogger;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Provider;
import org.apache.kafka.common.serialization.Serde;

public final class SerdeStore<T> {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private final ImmutableMap<Name, Provider<Serde<T>>> serdes;

  @Inject
  /* package */ SerdeStore(Map<Name, Provider<Serde<T>>> serdes) {
    this.serdes = ImmutableMap.copyOf(serdes);
  }

  public Serde<T> serde(Name name) {
    var serdeProvider = serdes.get(name);
    if (serdeProvider == null) {
      logger.atWarning().log("Serde %s not available", name);
      logger.atInfo().log(
          "Available serdes: %s",
          serdes.keySet().stream().map(Name::name).collect(Collectors.joining(", ")));
      throw new IllegalArgumentException(String.format("No serde for key '%s'", name));
    }
    return serdeProvider.get();
  }
}
