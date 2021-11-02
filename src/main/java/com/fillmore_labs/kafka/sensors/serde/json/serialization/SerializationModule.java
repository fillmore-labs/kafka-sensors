package com.fillmore_labs.kafka.sensors.serde.json.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.json.JsonDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.json.JsonSerializer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Singleton
  /* package */ static ObjectMapper mapper() {
    return JsonMapper.builder()
        .addModules(new Jdk8Module(), new JavaTimeModule(), new GuavaModule())
        .build();
  }

  @Provides
  /* package */ static Serializer<SensorStateJson> sensorStateSerializer(ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, SensorStateJson.class);
  }

  @Provides
  /* package */ static Deserializer<SensorStateJson> sensorStateDeserializer(ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, SensorStateJson.class);
  }

  @Provides
  /* package */ static Serializer<ReadingJson> readingSerializer(ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, ReadingJson.class);
  }

  @Provides
  /* package */ static Deserializer<ReadingJson> readingDeserializer(ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, ReadingJson.class);
  }

  @Provides
  /* package */ static Serializer<StateWithDurationJson> stateDurationSerializer(
      ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, StateWithDurationJson.class);
  }

  @Provides
  /* package */ static Deserializer<StateWithDurationJson> stateDurationDeserializer(
      ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, StateWithDurationJson.class);
  }
}
