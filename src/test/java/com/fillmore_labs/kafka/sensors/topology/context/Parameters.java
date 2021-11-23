package com.fillmore_labs.kafka.sensors.topology.context;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Named;

public final class Parameters implements Iterable<Formats> {
  private final ImmutableList<String> allFormats;

  @Inject
  /* package */ Parameters(@Named("encoding") Map<String, String> encodings) {
    this.allFormats = ImmutableList.copyOf(encodings.keySet());
  }

  @Override
  public Iterator<Formats> iterator() {
    return new RandomCombinations(allFormats);
  }

  private static final class RandomCombinations implements Iterator<Formats> {
    private final Iterator<String> inputs;
    private final Iterator<String> stores;
    private final Iterator<String> results;

    private RandomCombinations(List<String> allFormats) {
      var random = new Random(8_682_522_807_148_012L); // This gives reproducible results
      this.inputs = shuffeldFormats(allFormats, random).iterator();
      this.stores = shuffeldFormats(allFormats, random).iterator();
      this.results = shuffeldFormats(allFormats, random).iterator();
    }

    @Override
    public boolean hasNext() {
      var hasNext = inputs.hasNext();
      if (hasNext == stores.hasNext() && hasNext == results.hasNext()) {
        return hasNext;
      }
      throw new IllegalStateException("Inconsistent Iterators");
    }

    @Override
    public Formats next() {
      return new Formats(inputs.next(), stores.next(), results.next());
    }

    private static List<String> shuffeldFormats(List<String> allFormats, Random random) {
      var formats = new ArrayList<>(allFormats);
      Collections.shuffle(formats, random);
      return formats;
    }
  }
}
