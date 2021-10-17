package com.fillmore_labs.kafka.sensors.helper.confluent;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public final class SchemaRegistryRule implements TestRule {
  private static final String REGISTRY_SCOPE = "test";

  private final String registryScope;

  private @MonotonicNonNull SchemaRegistryClient registryClient;

  public SchemaRegistryRule() {
    this(REGISTRY_SCOPE);
  }

  public SchemaRegistryRule(String registryScope) {
    this.registryScope = registryScope;
  }

  public String registryUrl() {
    return "mock://" + registryScope;
  }

  public Map<String, String> configs() {
    return Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl());
  }

  public void reset() {
    assert registryClient != null : "@AssumeAssertion(nullness): before() not called";
    registryClient.reset();
    MockSchemaRegistry.dropScope(registryScope);
  }

  @EnsuresNonNull("registryClient")
  private void before() {
    registryClient = MockSchemaRegistry.getClientForScope(registryScope);
  }

  @RequiresNonNull("registryClient")
  private void after() {
    reset();
  }

  @Override
  public Statement apply(Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        before();
        try {
          base.evaluate();
        } finally {
          after();
        }
      }
    };
  }
}
