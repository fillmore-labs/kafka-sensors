package com.fillmore_labs.kafka.sensors.configuration;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import io.helidon.config.objectmapping.Value;
import java.util.List;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.immutables.value.Value.Style;

/** The main Kafka configuration. */
@Immutable
@Style(
    optionalAcceptNullable = true,
    passAnnotations = {Immutable.class})
@org.immutables.value.Value.Immutable
public abstract class KafkaConfiguration implements WithKafkaConfiguration {
  public static final String PREFIX = "kafka";

  /* package */ KafkaConfiguration() {}

  public static Builder builder() {
    return ImmutableKafkaConfiguration.builder();
  }

  public abstract ImmutableList<String> bootstrapServers();

  public abstract ImmutableList<String> schemaRegistry();

  public abstract String inputTopic();

  public abstract String resultTopic();

  public abstract Optional<String> clientId();

  public abstract static class Builder {
    @Value(key = "brokers")
    @CanIgnoreReturnValue
    public final Builder bootstrapServers(List<String> bootstrapServers) {
      return bootstrapServers((Iterable<String>) bootstrapServers);
    }

    @CanIgnoreReturnValue
    public abstract Builder bootstrapServers(Iterable<String> elements);

    @Value(key = "schema-registry")
    @CanIgnoreReturnValue
    public final Builder schemaRegistry(List<String> schemaRegistry) {
      return schemaRegistry((Iterable<String>) schemaRegistry);
    }

    @CanIgnoreReturnValue
    public abstract Builder schemaRegistry(Iterable<String> elements);

    @Value(key = "input-topic")
    @CanIgnoreReturnValue
    public abstract Builder inputTopic(String inputTopic);

    @Value(key = "result-topic")
    @CanIgnoreReturnValue
    public abstract Builder resultTopic(String resultTopic);

    @Value(key = "client-id")
    @CanIgnoreReturnValue
    public abstract Builder clientId(@Nullable String clientId);

    public abstract KafkaConfiguration build();
  }
}
