package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.kafka.common.serialization.Serde;
import org.junit.Test;

public abstract class ConsistencyTestBase {
  private final ImmutableMap<String, String> encodings;
  private final ImmutableMap<String, Serde<SensorState>> serdeMap;
  private final ImmutableMap<String, Serde<StateDuration>> serdeDurationMap;

  protected ConsistencyTestBase(
      Map<String, String> encodings,
      Map<String, Serde<Reading>> serdeMapReading,
      Map<String, Serde<SensorState>> serdeMap,
      Map<String, Serde<StateDuration>> serdeDurationMap) {
    this.encodings = ImmutableMap.copyOf(encodings);
    this.serdeMap = ImmutableMap.copyOf(serdeMap);
    this.serdeDurationMap = ImmutableMap.copyOf(serdeDurationMap);
  }

  protected abstract List<String> encodingValues();

  @Test
  public final void testEncodingValues() {
    var encodingValues = Set.copyOf(encodings.values());
    // This is the wrong way around. Is there an inverse to containsAtLeastElementsIn?
    assertThat(encodingValues()).containsAtLeastElementsIn(encodingValues);
  }

  @Test
  public final void testSerdes() {
    assertThat(serdeMap.keySet()).containsExactlyElementsIn(encodings.keySet());
  }

  @Test
  public final void testSerdeDurations() {
    assertThat(serdeDurationMap.keySet()).containsExactlyElementsIn(encodings.keySet());
  }
}
