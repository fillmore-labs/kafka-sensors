package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.schema_store.SchemaStoreModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroSerializer;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import java.util.Optional;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.message.SchemaStore;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, SchemaStoreModule.class})
public abstract class TestModule {
  private TestModule() {}

  @Provides
  @TestComponent.StateDurationTest
  /* package */ static Serializer<GenericRecord> stateDurationSerializer() {
    return new AvroSerializer<>(StateDurationTestSchema.MODEL, StateDurationTestSchema.SCHEMA);
  }

  @Provides
  @TestComponent.StateDurationTest
  /* package */ static Deserializer<GenericRecord> stateDurationDeserializer(
      Optional<SchemaStore> resolver) {
    return new AvroDeserializer<>(
        StateDurationTestSchema.MODEL, StateDurationTestSchema.SCHEMA, resolver.orElse(null));
  }

  @Provides
  @IntoSet
  /* package */ static Schema stateDurationTestSchema() {
    return StateDurationTestSchema.SCHEMA;
  }
}
