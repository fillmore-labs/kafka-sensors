package com.fillmore_labs.kafka.sensors.topology;

import java.util.function.Supplier;
import javax.inject.Inject;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.Stores;

/* package */ final class TopologyFactory implements Supplier<Topology> {
  private final TopologySettings settings;
  private final DurationTransformerFactory factory;

  @Inject
  /* package */ TopologyFactory(TopologySettings settings, DurationTransformerFactory factory) {
    this.settings = settings;
    this.factory = factory;
  }

  @Override
  public Topology get() {
    var inputTopic = settings.inputTopic();
    var consumed = Consumed.with(Serdes.String(), settings.inputSerde());

    var resultTopic = settings.resultTopic();
    var produced = Produced.with(Serdes.String(), settings.resultSerde());

    var storeName = settings.storeName();
    // Use an im-memory store
    var storeSupplier = Stores.inMemoryKeyValueStore(storeName);
    var stateStore =
        Stores.keyValueStoreBuilder(storeSupplier, Serdes.String(), settings.storeSerde());

    // name of the processor in the topology
    var processorName = Named.as("DURATION-PROCESSOR");

    var builder =
        // Initialize Kafka Streams DSL ...
        new StreamsBuilder()
            // ... and register the store builder
            .addStateStore(stateStore);

    // Now, define our topology
    var stateStoreNames = new String[] {storeName};
    // Input ...
    builder.stream(inputTopic, consumed)
        // ... Process ...
        .transform(() -> factory.create(storeName), processorName, stateStoreNames)
        // ... Result.
        .to(resultTopic, produced);

    return builder.build();
  }
}
