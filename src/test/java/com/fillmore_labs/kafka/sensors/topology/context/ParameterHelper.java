package com.fillmore_labs.kafka.sensors.topology.context;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.common.SerdeStore;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.inject.Inject;

public final class ParameterHelper implements Supplier<Iterable<?>> {
  private static final Name[] SERDES = {
    Name.PROTO, Name.JSON, Name.AVRO_DIRECT, Name.CONFLUENT_DIRECT, Name.ION_BINARY,
  };

  private final SerdeStore<SensorState> serdeStore;
  private final SerdeStore<SensorStateDuration> serdeStoreDuration;

  @Inject
  /* package */ ParameterHelper(
      SerdeStore<SensorState> serdeStore, SerdeStore<SensorStateDuration> serdeStoreDuration) {
    this.serdeStore = serdeStore;
    this.serdeStoreDuration = serdeStoreDuration;
  }

  private Object[] makeParameter(Name inputSerdes, Name storeSerdes, Name resultSerdes) {
    // Keep types in sync with TopologyTest
    var description =
        String.format("%s - %s - %s", inputSerdes.name(), storeSerdes.name(), resultSerdes.name());
    var inputSupplier = serdeStore.serde(inputSerdes);
    var storeSupplier = serdeStore.serde(storeSerdes);
    var resultSupplier = serdeStoreDuration.serde(resultSerdes);

    return new Object[] {description, inputSupplier, storeSupplier, resultSupplier};
  }

  private Stream<Object[]> parameters(Name inputSerdes, Name storeSerdes) {
    return Arrays.stream(SERDES)
        .map(resultSerdes -> makeParameter(inputSerdes, storeSerdes, resultSerdes));
  }

  private Stream<Object[]> parameters(Name inputSerdes) {
    return Arrays.stream(SERDES).flatMap(storeSerdes -> parameters(inputSerdes, storeSerdes));
  }

  private Stream<Object[]> parameters() {
    return Arrays.stream(SERDES).flatMap(this::parameters);
  }

  @Override
  public Iterable<?> get() {
    // All combinations of SERDES in every position
    return parameters().toList();
  }
}
