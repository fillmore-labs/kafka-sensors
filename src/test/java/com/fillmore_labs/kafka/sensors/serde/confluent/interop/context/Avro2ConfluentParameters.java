package com.fillmore_labs.kafka.sensors.serde.confluent.interop.context;

import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public final class Avro2ConfluentParameters extends Parameters {
  @Inject /* package */
  Avro2ConfluentParameters(
      Provider<TestComponent.SingleTestComponent.Builder> singleTestComponentBuilder,
      @Named("encoding") Map<String, String> encodings) {
    super(singleTestComponentBuilder, encodings);
  }

  @Override
  public Iterator<Object[]> iterator() {
    var objectstream = combinations(avroFormats, confluentFormats);

    return objectstream.iterator();
  }
}
