package com.fillmore_labs.kafka.sensors.serde.avro;

import com.fillmore_labs.kafka.sensors.serde.avro.generic.GenericModule;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.ReflectModule;
import com.fillmore_labs.kafka.sensors.serde.avro.specific.SpecificModule;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import java.util.Set;
import javax.inject.Singleton;
import org.apache.avro.Schema;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.message.SchemaStore.Cache;

@Module(includes = {SpecificModule.class, GenericModule.class, ReflectModule.class})
public abstract class AvroModule {
  private AvroModule() {}

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
