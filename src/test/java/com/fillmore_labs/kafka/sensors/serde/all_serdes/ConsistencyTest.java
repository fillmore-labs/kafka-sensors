package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import java.util.List;

public class ConsistencyTest extends ConsistencyTestBase {
  private static final String[] ENCODING_VALUES = {
    "avro", "confluent/avro", "confluent/json", "confluent/protobuf", "ion", "json", "protobuf"
  };

  public ConsistencyTest() {
    super(TestComponent.create()::injectMembers);
  }

  @Override
  protected List<String> encodingValues() {
    return List.of(ENCODING_VALUES);
  }
}
