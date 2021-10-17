package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.common.Name.Format;
import com.fillmore_labs.kafka.sensors.serde.confluent.interop.TestComponent.SingleTestComponent;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* package */ final class TestHelper {
  /* package */ static final String KAFKA_TOPIC = "topic";

  private TestHelper() {}

  /* package */ static SensorState standardSensorState() {
    var instant = Instant.ofEpochSecond(443634300L, 1_000L);

    return SensorState.builder().id("7331").time(instant).state(State.ON).build();
  }

  /* package */ static SensorStateDuration standardSensorStateDuration() {
    var event = standardSensorState();

    return SensorStateDuration.builder()
        .event(event)
        .duration(Duration.ofSeconds(15, 999_999_000L))
        .build();
  }

  /* package */ static List<Object[]> parametersWithDuration(String registryUrl) {
    var testComponent = TestComponent.builder().schemaRegistryUrl(registryUrl).build();
    return toParameters(testComponent, Format.CONFLUENT_AVRO, Format.AVRO);
  }

  /* package */ static List<Object[]> reverseParametersWithDuration(String registryUrl) {
    var testComponent = TestComponent.builder().schemaRegistryUrl(registryUrl).build();
    return toParameters(testComponent, Format.AVRO, Format.CONFLUENT_AVRO);
  }

  /**
   * This creates a list of all compatible {@link org.apache.kafka.common.serialization.Serializer}
   * - {@link org.apache.kafka.common.serialization.Deserializer} pairs to test.
   *
   * @return Serde pairs to test
   * @see org.junit.runners.Parameterized.Parameters
   */
  private static List<Object[]> toParameters(TestComponent testComponent, Format from, Format to) {
    return namesWithFormat(from)
        .flatMap(
            input ->
                namesWithFormat(to)
                    .map(
                        result ->
                            new Object[] {
                              String.format("%s - %s", input.name(), result.name()),
                              singleTestComponentSupplier(testComponent, input, result)
                            }))
        .toList();
  }

  private static Supplier<SingleTestComponent> singleTestComponentSupplier(
      TestComponent testComponent, Name input, Name result) {
    return () -> testComponent.subcomponent().input(input).result(result).build();
  }

  private static Stream<Name> allNames() {
    return Arrays.stream(Name.values());
  }

  private static Predicate<Name> hasFormat(Format format) {
    return name -> name.format() == format;
  }

  private static Stream<Name> namesWithFormat(Format format) {
    return allNames().filter(hasFormat(format));
  }
}
