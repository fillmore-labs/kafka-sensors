package com.fillmore_labs.kafka.sensors.benchmark;

import com.fillmore_labs.kafka.sensors.benchmark.context.BenchComponent;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.common.SerdeStore;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public /* open */ class ExecutionPlan {
  private static final String REGISTRY_SCOPE = "benchmark";
  private static final String REGISTRY_URL = "mock://" + REGISTRY_SCOPE;

  @Param public @MonotonicNonNull Name name;

  public @MonotonicNonNull Serializer<SensorStateDuration> serializer;
  public @MonotonicNonNull Deserializer<SensorStateDuration> deserializer;
  public @MonotonicNonNull SensorStateDuration data;
  public byte @MonotonicNonNull [] serialized;

  private final SchemaRegistryClient registryClient;
  private final SerdeStore<SensorStateDuration> serdeStore;

  public ExecutionPlan() {
    registryClient = MockSchemaRegistry.getClientForScope(REGISTRY_SCOPE);
    var benchComponent = BenchComponent.builder().schemaRegistryUrl(REGISTRY_URL).build();
    this.serdeStore = benchComponent.serdeStoreDuration();
  }

  @Setup(Level.Iteration)
  @RequiresNonNull("name")
  @EnsuresNonNull({"serializer", "deserializer", "data", "serialized", "registryClient"})
  public void setup() {
    var serde = serdeStore.serde(name);
    serializer = serde.serializer();
    deserializer = serde.deserializer();

    data = Constants.createData();

    serialized = serializer.serialize(Constants.TOPIC, data);
  }

  @TearDown(Level.Iteration)
  @RequiresNonNull({"serializer", "deserializer", "registryClient"})
  public void tearDown() {
    serializer.close();
    deserializer.close();

    registryClient.reset();
    MockSchemaRegistry.dropScope(REGISTRY_SCOPE);
  }
}
