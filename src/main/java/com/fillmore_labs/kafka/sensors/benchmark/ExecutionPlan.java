package com.fillmore_labs.kafka.sensors.benchmark;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
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

@State(Scope.Benchmark)
public /* open */ class ExecutionPlan {
  public static final String TOPIC = "topic";

  @Param({
    "json",
    "mixin",
    "gson",
    "gson-faster",
    "proto",
    "avro-generic",
    "avro-reflect",
    "avro-specific",
    "avro-specific-faster",
    "confluent-generic",
    "confluent-reflect",
    "confluent-specific",
    "confluent-specific-faster",
    "confluent-json",
    "confluent-proto",
    "ion-binary",
    "ion-text",
  })
  public @MonotonicNonNull String format;

  @Inject public @MonotonicNonNull Serializer<SensorStateDuration> serializer;
  @Inject public @MonotonicNonNull Deserializer<SensorStateDuration> deserializer;

  public @MonotonicNonNull SensorStateDuration data;
  public byte @MonotonicNonNull [] serialized;

  public ExecutionPlan() {}

  @Setup(Level.Trial)
  @RequiresNonNull("format")
  @EnsuresNonNull({"serializer", "deserializer", "data", "serialized"})
  public void setup() {
    BenchComponent.builder().format(format).build().injectMembers(this);
    assert serializer != null : "@AssumeAssertion(nullness): inject() failed";
    assert deserializer != null : "@AssumeAssertion(nullness): inject() failed";

    data = createData();
    serialized = serializer.serialize(TOPIC, data);
  }

  private static SensorStateDuration createData() {
    var instant = Instant.ofEpochSecond(443_634_300L);

    var event = SensorState.builder().id("7331").time(instant).state(SensorState.State.ON).build();

    return SensorStateDuration.builder().event(event).duration(Duration.ofSeconds(15)).build();
  }
}
