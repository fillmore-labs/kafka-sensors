package com.fillmore_labs.kafka.sensors.serde.avro.schema_store;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import jakarta.inject.Singleton;
import java.util.Set;
import org.apache.avro.Schema;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.message.SchemaStore.Cache;

@Module
public abstract class SchemaStoreModule {
  private SchemaStoreModule() {}

  @Provides
  @Singleton
  /* package */ static Cache resolver(Set<Schema> schemata) {
    var resolver = new Cache();
    schemata.forEach(resolver::addSchema);
    return resolver;
  }

  @Binds
  /* package */ abstract SchemaStore schemaStore(Cache resolver);
}
