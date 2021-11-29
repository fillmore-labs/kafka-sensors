package com.fillmore_labs.kafka.sensors.serde.confluent.common;

import dagger.Module;
import dagger.Provides;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import java.util.List;
import java.util.Random;
import javax.inject.Singleton;

@Module
public final class SchemaRegistryModule {
  private SchemaRegistryModule() {}

  @Provides
  @Singleton
  @SchemaRegistryUrl
  /* package */ static String schemaRegistryUrl() {
    var random = new Random().nextInt(10_000);
    return "mock://test-" + random;
  }

  @Provides
  /* package */ static SchemaRegistryClient schemaRegistryClient(
      @SchemaRegistryUrl String registryUrl) {
    var scope = MockSchemaRegistry.validateAndMaybeGetMockScope(List.of(registryUrl));
    if (scope == null) {
      throw new IllegalArgumentException("Not a mock registry: " + registryUrl);
    }
    return MockSchemaRegistry.getClientForScope(scope);
  }
}
