package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.json.JsonTestHelper;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.inject.Inject;
import org.apache.kafka.common.serialization.Deserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class CanReadTest {
  private static final String TOPIC = "topic";

  @Inject @MixIn /* package */ Deserializer<SensorState> deserializer;

  private final byte[] encoded;

  public CanReadTest(String message) {
    TestComponent.create().inject(this);
    assert deserializer != null : "@AssumeAssertion(nullness): inject() failed";
    this.encoded = message.getBytes(StandardCharsets.UTF_8);
  }

  @Parameters(name = "{index}: {0}")
  public static List<String> parameters() {
    return JsonTestHelper.sampleInput();
  }

  @Test
  public void canReadSample() {
    var decoded = deserializer.deserialize(TOPIC, encoded);
    assertThat(decoded).isNotNull();
  }
}
