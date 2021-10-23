package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import dagger.Module;
import dagger.Provides;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.Map;
import javax.inject.Qualifier;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module
/* package */ abstract class SingleTestModule {
  private SingleTestModule() {}

  @Provides
  /* package */ static Serializer<SensorState> serializer(
      @Serialization String format, Map<String, Serde<SensorState>> serdeMap) {
    var serde = serdeMap.get(format);
    if (serde == null) {
      throw new IllegalArgumentException(String.format("Unknown format: %s", format));
    }
    return serde.serializer();
  }

  @Provides
  /* package */ static Deserializer<SensorState> deserializer(
      @Deserialization String format, Map<String, Serde<SensorState>> serdeMap) {
    var serde = serdeMap.get(format);
    if (serde == null) {
      throw new IllegalArgumentException(String.format("Unknown format: %s", format));
    }
    return serde.deserializer();
  }

  @Provides
  /* package */ static Serializer<SensorStateDuration> serializerDuration(
      @Serialization String format, Map<String, Serde<SensorStateDuration>> serdeMap) {
    var serde = serdeMap.get(format);
    if (serde == null) {
      throw new IllegalArgumentException(String.format("Unknown format: %s", format));
    }
    return serde.serializer();
  }

  @Provides
  /* package */ static Deserializer<SensorStateDuration> deserializerDuration(
      @Deserialization String format, Map<String, Serde<SensorStateDuration>> serdeMap) {
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
