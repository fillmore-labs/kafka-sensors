package com.fillmore_labs.kafka.sensors.serde.json_iso.serialization;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.blackbird.BlackbirdModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.ReadingJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.StateWithDurationJson;
import com.fillmore_labs.kafka.sensors.serde.serializer.json.JsonDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.json.JsonSerializer;
import dagger.Module;
import dagger.Provides;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Singleton
  @JsonIso
  /* package */ static ObjectMapper mapper() {
    return JsonMapper.builder()
        .addModules(new Jdk8Module(), new JavaTimeModule(), new GuavaModule())
        .addModule(new BlackbirdModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .build();
  }

  @Provides
  @JsonIso
  /* package */ static Serializer<SensorStateJson> sensorStateSerializer(
      @JsonIso ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, SensorStateJson.class);
  }

  @Provides
  @JsonIso
  /* package */ static Deserializer<SensorStateJson> sensorStateDeserializer(
      @JsonIso ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, SensorStateJson.class);
  }

  @Provides
  @JsonIso
  /* package */ static Serializer<ReadingJson> readingSerializer(@JsonIso ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, ReadingJson.class);
  }

  @Provides
  @JsonIso
  /* package */ static Deserializer<ReadingJson> readingDeserializer(@JsonIso ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, ReadingJson.class);
  }

  @Provides
  @JsonIso
  /* package */ static Serializer<StateWithDurationJson> stateDurationSerializer(
      @JsonIso ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, StateWithDurationJson.class);
  }

  @Provides
  @JsonIso
  /* package */ static Deserializer<StateWithDurationJson> stateDurationDeserializer(
      @JsonIso ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, StateWithDurationJson.class);
  }

  @Qualifier
  @Documented
  @Retention(RUNTIME)
  public @interface JsonIso {}
}
