package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.common.Name.Format;
import com.fillmore_labs.kafka.sensors.serde.common.SerdeStore;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import dagger.BindsInstance;
import dagger.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.inject.Singleton;

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

  /* package */ static List<Object[]> parameters(String registryUrl) {
    var testComponent = TestComponent.builder().schemaRegistryUrl(registryUrl).build();
    return toParameters(testComponent.serdeStore());
  }

  /* package */ static List<Object[]> parametersWithDuration(String registryUrl) {
    var testComponent = TestComponent.builder().schemaRegistryUrl(registryUrl).build();
    return toParameters(testComponent.serdeStoreDuration());
  }

  /**
   * This creates a list of all compatible {@link org.apache.kafka.common.serialization.Serializer}
   * - {@link org.apache.kafka.common.serialization.Deserializer} pairs to test.
   *
   * @return Serde pairs to test
   * @see org.junit.runners.Parameterized.Parameters
   */
  private static List<Object[]> toParameters(SerdeStore<?> serdeStore) {
    return allNames()
        .flatMap(
            inputSerde ->
                namesWithFormat(inputSerde.format())
                    .map(
                        resultSerde ->
                            new Object[] {
                              String.format("%s - %s", inputSerde.name(), resultSerde.name()),
                              serdeStore.serde(inputSerde),
                              serdeStore.serde(resultSerde),
                            }))
        .toList();
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

  @Singleton
  @Component(modules = AllSerdesModule.class)
  /* package */ interface TestComponent {
    static Builder builder() {
      return DaggerTestHelper_TestComponent.builder();
    }

    SerdeStore<SensorState> serdeStore();

    SerdeStore<SensorStateDuration> serdeStoreDuration();

    @Component.Builder
    interface Builder {
      @BindsInstance
      Builder schemaRegistryUrl(@SchemaRegistryUrl String schemaRegistryUrl);

      TestComponent build();
    }
  }
}
