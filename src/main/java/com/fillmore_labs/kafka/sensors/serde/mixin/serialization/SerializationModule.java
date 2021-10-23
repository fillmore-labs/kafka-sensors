package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fillmore_labs.kafka.sensors.model.ImmutableSensorState;
import com.fillmore_labs.kafka.sensors.model.ImmutableSensorStateDuration;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
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
  @MixIn
  /* package */ static ObjectMapper mixInMapper() {
    return JsonMapper.builder()
        .addModules(new Jdk8Module(), new JavaTimeModule(), new GuavaModule())
        .addMixIn(SensorState.class, SensorStateMixIn.class)
        .addMixIn(SensorState.State.class, SensorStateMixIn.StateMixIn.class)
        .addMixIn(ImmutableSensorState.Builder.class, SensorStateMixIn.BuilderMixIn.class)
        .addMixIn(SensorStateDuration.class, SensorStateDurationMixIn.class)
        .addMixIn(
            ImmutableSensorStateDuration.Builder.class, SensorStateDurationMixIn.BuilderMixIn.class)
        .build();
  }

  @Provides
  @MixIn
  /* package */ static Serializer<SensorState> sensorStateSerializer(@MixIn ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, SensorState.class);
  }

  @Provides
  @MixIn
  /* package */ static Deserializer<SensorState> sensorStateDeserializer(
      @MixIn ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, SensorState.class);
  }

  @Provides
  @MixIn
  /* package */ static Serializer<SensorStateDuration> sensorStateDurationSerializer(
      @MixIn ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, SensorStateDuration.class);
  }

  @Provides
  @MixIn
  /* package */ static Deserializer<SensorStateDuration> sensorStateDurationDeserializer(
      @MixIn ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, SensorStateDuration.class);
  }
}
