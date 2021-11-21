package com.fillmore_labs.kafka.sensors.serde.confluent.common;

import dagger.Module;
import dagger.Provides;
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
}
