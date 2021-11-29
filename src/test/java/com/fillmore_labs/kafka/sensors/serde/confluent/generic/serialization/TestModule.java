package com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.StateDurationTestSchema;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, SchemaRegistryModule.class})
public abstract class TestModule {
  private TestModule() {}

  @Provides
  @TestComponent.StateDurationTest
  /* package */ static Serializer<GenericRecord> stateDurationSerializer(
      @SchemaRegistryUrl String registryUrl) {
    return SerializationModule.createSerializer(registryUrl);
  }

  @Provides
  @TestComponent.StateDurationTest
  /* package */ static Deserializer<GenericRecord> stateDurationDeserializer(
      @SchemaRegistryUrl String registryUrl) {
    return SerializationModule.createDeserializer(StateDurationTestSchema.SCHEMA, registryUrl);
  }

  @Provides
  @IntoSet
  /* package */ static Schema stateDurationTestSchema() {
    return StateDurationTestSchema.SCHEMA;
  }
}
