package com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import dagger.Component;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Singleton
@Component(modules = TestModule.class)
public interface TestComponent {

  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  @Confluent.StateDuration
  Serializer<GenericRecord> serializer();

  @Confluent.StateDuration
  Deserializer<GenericRecord> deserializer();

  @StateDurationTest
  Serializer<GenericRecord> testSerializer();

  @StateDurationTest
  Deserializer<GenericRecord> testDeserializer();

  @Qualifier
  @Documented
  @Retention(RUNTIME)
  @interface StateDurationTest {}
}
