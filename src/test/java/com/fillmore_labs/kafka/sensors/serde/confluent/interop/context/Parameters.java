package com.fillmore_labs.kafka.sensors.serde.confluent.interop.context;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.inject.Provider;

public abstract class Parameters implements Iterable<Object[]> {
  protected final List<String> avroFormats;
  protected final List<String> confluentFormats;
  private final Provider<TestComponent.SingleTestComponent.Builder> singleTestComponentBuilder;

  protected Parameters(
      Provider<TestComponent.SingleTestComponent.Builder> singleTestComponentBuilder,
      Map<String, String> encodings) {
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

  protected final Stream<Object[]> combinations(
      List<String> serializers, List<String> deserializers) {
    return serializers.stream()
        .flatMap(
            serializer ->
                deserializers.stream().map(deserializer -> createParam(serializer, deserializer)));
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
