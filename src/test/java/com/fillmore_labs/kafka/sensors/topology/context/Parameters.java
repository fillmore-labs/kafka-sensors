package com.fillmore_labs.kafka.sensors.topology.context;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public final class Parameters implements Iterable<Object[]> {
  private final Provider<SingleTestComponent.Builder> singleTestComponentBuilder;
  private final ImmutableList<String> allFormats;

  @Inject
  /* package */ Parameters(
      Provider<SingleTestComponent.Builder> singleTestComponentBuilder,
      @Named("encoding") Map<String, String> encodings) {
    this.singleTestComponentBuilder = singleTestComponentBuilder;
    this.allFormats = ImmutableList.copyOf(encodings.keySet());
  }

  private Stream<Object[]> combinations() {
    return allFormats.stream()
        .flatMap(
            input ->
                allFormats.stream()
                    .flatMap(
                        store ->
                            allFormats.stream().map(result -> new Formats(input, store, result))))
        .map(this::createParam);
  }

  private Object[] createParam(Formats formats) {
    return new Object[] {
      formats.toString(), singleTestComponentBuilder.get().formats(formats).build()
    };
  }

  @Override
  public Iterator<Object[]> iterator() {
    return combinations().iterator();
  }
}
