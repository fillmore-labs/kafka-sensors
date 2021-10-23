package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Serde;
import org.junit.Test;

public abstract class ConsistencyTestBase {
  @Inject /* package */
  @Named("encoding")
  Map<String, String> encodings;

  @Inject /* package */ Map<String, Serde<SensorState>> serdeMap;

  @Inject /* package */ Map<String, Serde<SensorStateDuration>> serdeDurationMap;

  protected ConsistencyTestBase(Injector<ConsistencyTestBase> injector) {
    injector.injectMembers(this);

    assert encodings != null : "@AssumeAssertion(nullness): inject() failed";
    assert serdeMap != null : "@AssumeAssertion(nullness): inject() failed";
    assert serdeDurationMap != null : "@AssumeAssertion(nullness): inject() failed";
  }

  protected abstract List<String> encodingValues();

  @Test
  public void testEncodingValues() {
    var encodingValues = encodings.values().stream().collect(Collectors.toUnmodifiableSet());
    assertThat(encodingValues).containsExactlyElementsIn(encodingValues());
  }

  @Test
  public void testSerdes() {
    assertThat(serdeMap.keySet()).containsExactlyElementsIn(encodings.keySet());
  }

  @Test
  public void testSerdeDurations() {
    assertThat(serdeDurationMap.keySet()).containsExactlyElementsIn(encodings.keySet());
  }
}
