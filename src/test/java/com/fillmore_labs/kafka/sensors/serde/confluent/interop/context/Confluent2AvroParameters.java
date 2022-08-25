package com.fillmore_labs.kafka.sensors.serde.confluent.interop.context;

import jakarta.inject.Inject;
import java.util.Iterator;

public final class Confluent2AvroParameters implements Iterable<Object[]> {
  private final BaseParameters parameters;

  @Inject
  /* package */ Confluent2AvroParameters(BaseParameters parameters) {
    this.parameters = parameters;
  }

  @Override
  public Iterator<Object[]> iterator() {
    return parameters.confluent2AvroCombinations();
  }
}
