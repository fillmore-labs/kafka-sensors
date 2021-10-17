package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.configuration.App;
import com.fillmore_labs.kafka.sensors.configuration.KafkaConfiguration;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.util.function.Supplier;
import javax.inject.Inject;
import javax.inject.Provider;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueTransformerSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

public final class TopologyFactory implements Supplier<Topology> {
  private final KafkaConfiguration configuration;
  private final Serde<SensorState> inputSerde;
  private final Serde<SensorState> storeSerde;
  private final Serde<SensorStateDuration> resultSerde;
  private final ValueTransformerSupplier<SensorState, SensorStateDuration> transformerSupplier;

  @Inject
  /* package */ TopologyFactory(
      KafkaConfiguration configuration,
      @App.InputSerde Serde<SensorState> inputSerde,
      @App.StoreSerde Serde<SensorState> storeSerde,
      @App.ResultSerde Serde<SensorStateDuration> resultSerde,
      Provider<DurationProcessor> processorProvider) {
    this.configuration = configuration;
    this.inputSerde = inputSerde;
    this.storeSerde = storeSerde;
    this.resultSerde = resultSerde;
    this.transformerSupplier = processorProvider::get;
  }

  @Override
  public Topology get() {
    var inputTopic = configuration.inputTopic();
    var consumed = Consumed.with(Serdes.String(), inputSerde);

    var resultTopic = configuration.resultTopic();
    var produced = Produced.with(Serdes.String(), resultSerde);

    var stateStore = stateStore();
    var stateStoreNames = new String[] {DurationProcessor.SENSOR_STATES};

    var builder =
        // Initialize Kafka Streams DSL ...
        new StreamsBuilder()
            // ... and register the store builder
            .addStateStore(stateStore);

    // name of the processor in the topology
    var processorName = Named.as("DURATION-PROCESSOR");

    // Now, define our topology
    builder.stream(inputTopic, consumed)
        .transformValues(transformerSupplier, processorName, stateStoreNames)
        .to(resultTopic, produced);

    return builder.build();
  }

  /**
   * Create a builder for our state store.
   *
   * @return Our state store builder
   */
  private StoreBuilder<KeyValueStore<String, SensorState>> stateStore() {
    return Stores.keyValueStoreBuilder(
        Stores.inMemoryKeyValueStore(DurationProcessor.SENSOR_STATES), Serdes.String(), storeSerde);
  }
}
