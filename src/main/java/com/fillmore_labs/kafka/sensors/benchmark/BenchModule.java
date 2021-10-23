package com.fillmore_labs.kafka.sensors.benchmark;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import dagger.Module;
import dagger.Provides;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.Map;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module
/* package */ abstract class BenchModule {
  private BenchModule() {}

  @Provides
  /* package */ static Serializer<SensorState> serializer(
      @Format String format, Map<String, Serde<SensorState>> serdeMap) {
    var serde = serdeMap.get(format);
    if (serde == null) {
      throw new IllegalArgumentException(String.format("Unknown format: %s", format));
    }
    return serde.serializer();
  }

  @Provides
  /* package */ static Deserializer<SensorState> deserializer(
      @Format String format, Map<String, Serde<SensorState>> serdeMap) {
    var serde = serdeMap.get(format);
    if (serde == null) {
      throw new IllegalArgumentException(String.format("Unknown format: %s", format));
    }
    return serde.deserializer();
  }

  @Provides
  /* package */ static Serializer<SensorStateDuration> serializerDuration(
      @Format String format, Map<String, Serde<SensorStateDuration>> serdeMap) {
    var serde = serdeMap.get(format);
    if (serde == null) {
      throw new IllegalArgumentException(String.format("Unknown format: %s", format));
    }
    return serde.serializer();
  }

  @Provides
  /* package */ static Deserializer<SensorStateDuration> deserializerDuration(
      @Format String format, Map<String, Serde<SensorStateDuration>> serdeMap) {
    var serde = serdeMap.get(format);
    if (serde == null) {
      throw new IllegalArgumentException(String.format("Unknown format: %s", format));
    }
    return serde.deserializer();
  }

  @Provides
  @Singleton
  @SchemaRegistryUrl
  /* package */ static String schemaRegistryUrl(@Format String format) {
    return "mock://benchmark-" + format;
  }

  @Qualifier
  @Documented
  @Retention(RUNTIME)
  public @interface Format {}
}
