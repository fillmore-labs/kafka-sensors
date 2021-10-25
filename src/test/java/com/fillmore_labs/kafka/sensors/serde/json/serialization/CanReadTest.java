package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fillmore_labs.kafka.sensors.helper.json.JsonTestHelper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.kafka.common.serialization.Deserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class CanReadTest {
  private static final String TOPIC = "topic";

  private final Deserializer<SensorStateJson> deserializer;
  private final byte[] encoded;

  public CanReadTest(String message) {
    var testComponent = TestComponent.create();
    this.deserializer = testComponent.deserializer();
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
