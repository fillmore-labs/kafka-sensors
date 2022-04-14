package com.fillmore_labs.kafka.sensors.serde.confluent.interop.context;

import java.util.Iterator;
import javax.inject.Inject;

public final class Avro2ConfluentParameters implements Iterable<Object[]> {
  private final BaseParameters parameters;

  @Inject
  /* package */ Avro2ConfluentParameters(BaseParameters parameters) {
    this.parameters = parameters;
  }

  @Override
  public Iterator<Object[]> iterator() {
    return parameters.avro2ConfluentCombinations();
  }
}
