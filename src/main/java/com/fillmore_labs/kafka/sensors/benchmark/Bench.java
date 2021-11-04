package com.fillmore_labs.kafka.sensors.benchmark;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(AverageTime)
@OutputTimeUnit(NANOSECONDS)
@Fork(1)
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 5, time = 5)
public /* open */ class Bench {
  @Benchmark
  @RequiresNonNull({"#1.deserializer", "#1.serialized"})
  public final Object deserialize(ExecutionPlan plan) {
    return plan.deserializer.deserialize(ExecutionPlan.TOPIC, plan.serialized);
  }

  @Benchmark
  @RequiresNonNull({"#1.serializer", "#1.data"})
  public final Object serialize(ExecutionPlan plan) {
    return plan.serializer.serialize(ExecutionPlan.TOPIC, plan.data);
  }
}
