package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.all_serdes.AllSerdesModule;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.common.SerdeStore;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.initialization.qual.UnderInitialization;

@Singleton
@Component(modules = TestComponent.TestModule.class)
public interface TestComponent {
  static Builder builder() {
    return DaggerTestComponent.builder();
  }

  SingleTestComponent.Builder subcomponent();

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder schemaRegistryUrl(@SchemaRegistryUrl String schemaRegistryUrl);

    TestComponent build();
  }

  @Target({METHOD, PARAMETER, FIELD})
  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface InputName {}

  @Target({METHOD, PARAMETER, FIELD})
  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface ResultName {}

  @Subcomponent(modules = SingleTestModule.class)
  interface SingleTestComponent {
    void inject(@UnderInitialization Confluent2AvroTest test);

    void inject(@UnderInitialization Avro2ConfluentTest test);

    @Subcomponent.Builder
    interface Builder {
      @BindsInstance
      Builder input(@InputName Name name);

      @BindsInstance
      Builder result(@ResultName Name name);

      SingleTestComponent build();
    }
  }

  @Module(includes = AllSerdesModule.class, subcomponents = SingleTestComponent.class)
  abstract class TestModule {
    private TestModule() {}
  }

  @Module
  abstract class SingleTestModule {
    private SingleTestModule() {}

    @Provides
    @SuppressWarnings("CloseableProvides")
    /* package */ static final Serializer<SensorStateDuration> serializer(
        @InputName Name name, SerdeStore<SensorStateDuration> store) {
      return store.serde(name).serializer();
    }

    @Provides
    @SuppressWarnings("CloseableProvides")
    /* package */ static final Deserializer<SensorStateDuration> deserializer(
        @ResultName Name name, SerdeStore<SensorStateDuration> store) {
      return store.serde(name).deserializer();
    }
  }
}
