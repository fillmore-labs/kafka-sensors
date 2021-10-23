package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimaps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public final class Parameters implements Iterable<Object[]> {
  private final Provider<SingleTestComponent.Builder> singleTestComponentBuilder;

  private final ImmutableMultimap<String, String> inverseEncodings;

  @Inject /* package */
  Parameters(
      Provider<SingleTestComponent.Builder> singleTestComponentBuilder,
      @Named("encoding") Map<String, String> encodings) {
    this.inverseEncodings = ImmutableMultimap.copyOf(Multimaps.forMap(encodings)).inverse();
    this.singleTestComponentBuilder = singleTestComponentBuilder;
  }

  @Override
  public Iterator<Object[]> iterator() {
    var objectstream =
        inverseEncodings.keySet().stream()
            .flatMap(encoding -> combinations(inverseEncodings.get(encoding)));

    return objectstream.iterator();
  }

  private Stream<Object[]> combinations(Collection<String> formats) {
    return formats.stream()
        .flatMap(
            serializer ->
                formats.stream().map(deserializer -> createParam(serializer, deserializer)));
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
