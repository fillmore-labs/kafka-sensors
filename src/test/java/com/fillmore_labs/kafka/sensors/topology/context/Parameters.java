package com.fillmore_labs.kafka.sensors.topology.context;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.inject.Named;

public final class Parameters implements Iterable<Formats> {
  private final ImmutableList<String> allFormats;

  @Inject
  /* package */ Parameters(@Named("encoding") Map<String, String> encodings) {
    this.allFormats = ImmutableList.copyOf(encodings.keySet());
  }

  private Stream<Formats> combinations() {
    return allFormats.stream()
        .flatMap(
            input ->
                allFormats.stream()
                    .flatMap(
                        store ->
                            allFormats.stream().map(result -> new Formats(input, store, result))));
  }

  @Override
  public Iterator<Formats> iterator() {
    return combinations().iterator();
  }
}
