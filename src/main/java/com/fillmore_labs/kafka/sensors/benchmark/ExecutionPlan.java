package com.fillmore_labs.kafka.sensors.benchmark;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import java.time.Duration;
import java.time.Instant;
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

  public final StateDuration data;

  @Param({
    "json",
    "mixin",
    "gson",
    "gson-faster",
    "proto",
    "thrift",
    "avro-specific",
    "avro-specific-faster",
    "avro-generic",
    "avro-reflect",
    "confluent-specific",
    "confluent-specific-faster",
    "confluent-generic",
    "confluent-reflect",
    "confluent-json",
    "confluent-proto",
    "ion-binary",
    "ion-text",
  })
  public @MonotonicNonNull String format;

  public @MonotonicNonNull Serializer<StateDuration> serializer;
  public @MonotonicNonNull Deserializer<StateDuration> deserializer;
  public byte @MonotonicNonNull [] serialized;

  public ExecutionPlan() {
    var instant = Instant.ofEpochSecond(443_634_300L);

    var reading = Reading.builder().time(instant).position(Reading.Position.ON).build();

    this.data =
        StateDuration.builder()
            .id("7331")
            .reading(reading)
            .duration(Duration.ofSeconds(15))
            .build();
  }

  @Setup(Level.Trial)
  @RequiresNonNull("format")
  @EnsuresNonNull({"serializer", "deserializer", "serialized"})
  public final void setup() {
    var benchComponent = BenchComponent.builder().format(format).build();
    serializer = benchComponent.serializer();
    deserializer = benchComponent.deserializer();

    serialized = serializer.serialize(TOPIC, data);
  }
}
