package com.fillmore_labs.kafka.sensors.serde.confluent.interop.context;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import javax.inject.Provider;

/* package */ final class BaseParameters {
  private final List<String> avroFormats;
  private final List<String> confluentFormats;
  private final Provider<TestComponent.SingleTestComponent.Builder> singleTestComponentBuilder;

  @Inject
  /* package */ BaseParameters(
      Provider<TestComponent.SingleTestComponent.Builder> singleTestComponentBuilder,
      @Named("encoding") Map<String, String> encodings) {
    this.avroFormats = encodingsWithFormat(encodings, "avro");
    this.confluentFormats = encodingsWithFormat(encodings, "confluent/avro");
    this.singleTestComponentBuilder = singleTestComponentBuilder;
  }

  private static List<String> encodingsWithFormat(Map<String, String> encodings, String format) {
    return encodings.entrySet().stream().filter(hastFormat(format)).map(Entry::getKey).toList();
  }

  private static Predicate<Map.Entry<String, String>> hastFormat(String format) {
    return entry -> entry.getValue().equals(format);
  }

  private Iterator<Object[]> combinations(List<String> serializers, List<String> deserializers) {
    return serializers.stream()
        .flatMap(
            serializer ->
                deserializers.stream().map(deserializer -> createParam(serializer, deserializer)))
        .iterator();
  }

  /* package */ Iterator<Object[]> avro2ConfluentCombinations() {
    return combinations(avroFormats, confluentFormats);
  }

  /* package */ Iterator<Object[]> confluent2AvroCombinations() {
    return combinations(confluentFormats, avroFormats);
  }

  private Object[] createParam(String serializer, String deserializer) {
    return new Object[] {
      serializer + " -> " + deserializer,
      singleTestComponentBuilder
          .get()
          .serializationFormat(serializer)
          .deserializationFormat(deserializer)
          .build()
    };
  }
}
