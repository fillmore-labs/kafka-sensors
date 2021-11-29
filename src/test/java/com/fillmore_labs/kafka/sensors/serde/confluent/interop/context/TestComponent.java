package com.fillmore_labs.kafka.sensors.serde.confluent.interop.context;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.AvroModule;
import com.fillmore_labs.kafka.sensors.serde.avro.schema_store.SchemaStoreModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.ConfluentAvroModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.interop.Avro2Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.interop.Confluent2Avro;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.Map;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Singleton
@Component(modules = TestComponent.TestModule.class)
public interface TestComponent {
  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  Avro2ConfluentParameters avro2ConfluentParameters();

  Confluent2AvroParameters confluent2AvroParameters();

  @Subcomponent(modules = SingleTestModule.class)
  interface SingleTestComponent {
    Avro2Confluent avro2Confluent();

    Confluent2Avro confluent2Avro();

    Serializer<StateDuration> serializer();

    Deserializer<StateDuration> deserializer();

    @Subcomponent.Builder
    interface Builder {
      @BindsInstance
      Builder serializationFormat(@SingleTestModule.Serialization String format);

      @BindsInstance
      Builder deserializationFormat(@SingleTestModule.Deserialization String format);

      SingleTestComponent build();
    }
  }

  @Module(
      includes = {
        AvroModule.class,
        ConfluentAvroModule.class,
        SchemaStoreModule.class,
        SchemaRegistryModule.class
      },
      subcomponents = SingleTestComponent.class)
  /* package */ abstract class TestModule {
    private TestModule() {}
  }

  @Module
  /* package */ abstract class SingleTestModule {
    private SingleTestModule() {}

    @Provides
    /* package */ static Serializer<StateDuration> serializerDuration(
        @Serialization String format, Map<String, Serde<StateDuration>> serdeMap) {
      var serde = serdeMap.get(format);
      if (serde == null) {
        throw new IllegalArgumentException(String.format("Unknown format: %s", format));
      }
      return serde.serializer();
    }

    @Provides
    /* package */ static Deserializer<StateDuration> deserializerDuration(
        @Deserialization String format, Map<String, Serde<StateDuration>> serdeMap) {
      var serde = serdeMap.get(format);
      if (serde == null) {
        throw new IllegalArgumentException(String.format("Unknown format: %s", format));
      }
      return serde.deserializer();
    }

    @Qualifier
    @Documented
    @Retention(RUNTIME)
    public @interface Serialization {}

    @Qualifier
    @Documented
    @Retention(RUNTIME)
    public @interface Deserialization {}
  }
}
