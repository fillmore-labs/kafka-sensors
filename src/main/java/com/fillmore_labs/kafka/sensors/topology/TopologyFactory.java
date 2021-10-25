package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.logic.DurationProcessorFactory;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.util.function.Supplier;
import javax.inject.Inject;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueTransformerWithKeySupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

/* package */ final class TopologyFactory implements Supplier<Topology> {
  private final TopologySettings settings;
  private final DurationProcessorFactory processorFactory;

  @Inject
  /* package */ TopologyFactory(
      TopologySettings settings, DurationProcessorFactory processorFactory) {
    this.settings = settings;
    this.processorFactory = processorFactory;
  }

  /**
   * Create a builder for our state store.
   *
   * @return Our state store builder
   */
  private static StoreBuilder<KeyValueStore<String, SensorState>> stateStore(
      String storeName, Serde<SensorState> storeSerde) {
    return Stores.keyValueStoreBuilder(
        Stores.inMemoryKeyValueStore(storeName), Serdes.String(), storeSerde);
  }

  @Override
  public Topology get() {
    var inputTopic = settings.inputTopic();
    var consumed = Consumed.with(Serdes.String(), settings.inputSerde());

    var resultTopic = settings.resultTopic();
    var produced = Produced.with(Serdes.String(), settings.resultSerde());

    var storeName = settings.storeName();
    var stateStore = stateStore(storeName, settings.storeSerde());
    var stateStoreNames = new String[] {storeName};

    var builder =
        // Initialize Kafka Streams DSL ...
        new StreamsBuilder()
            // ... and register the store builder
            .addStateStore(stateStore);

    // name of the processor in the topology
    var processorName = Named.as("DURATION-PROCESSOR");

    ValueTransformerWithKeySupplier<String, SensorState, SensorStateDuration> transformerSupplier =
        () -> processorFactory.create(storeName);

    // Now, define our topology
    builder.stream(inputTopic, consumed)
        .transformValues(transformerSupplier, processorName, stateStoreNames)
        .to(resultTopic, produced);

    return builder.build();
  }
}
