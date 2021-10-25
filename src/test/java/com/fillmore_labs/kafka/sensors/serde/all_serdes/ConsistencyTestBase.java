package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.kafka.common.serialization.Serde;
import org.junit.Test;

public abstract class ConsistencyTestBase {
  private final ImmutableMap<String, String> encodings;
  private final ImmutableMap<String, Serde<SensorState>> serdeMap;
  private final ImmutableMap<String, Serde<SensorStateDuration>> serdeDurationMap;

  protected ConsistencyTestBase(
      Map<String, String> encodings,
      Map<String, Serde<SensorState>> serdeMap,
      Map<String, Serde<SensorStateDuration>> serdeDurationMap) {
    this.encodings = ImmutableMap.copyOf(encodings);
    this.serdeMap = ImmutableMap.copyOf(serdeMap);
    this.serdeDurationMap = ImmutableMap.copyOf(serdeDurationMap);
  }

  protected abstract List<String> encodingValues();

  @Test
  public final void testEncodingValues() {
    var encodingValues = encodings.values().stream().collect(Collectors.toUnmodifiableSet());
    assertThat(encodingValues).containsExactlyElementsIn(encodingValues());
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
