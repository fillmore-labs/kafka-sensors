package com.fillmore_labs.kafka.sensors.serde.mixin.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.ImmutableEvent;
import com.fillmore_labs.kafka.sensors.model.ImmutableSensorState;
import com.fillmore_labs.kafka.sensors.model.ImmutableStateDuration;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
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
        .addMixIn(Event.class, EventMixIn.class)
        .addMixIn(Event.Position.class, EventMixIn.PositionMixIn.class)
        .addMixIn(ImmutableEvent.Builder.class, EventMixIn.BuilderMixIn.class)
        .addMixIn(SensorState.class, SensorStateMixIn.class)
        .addMixIn(ImmutableSensorState.Builder.class, SensorStateMixIn.BuilderMixIn.class)
        .addMixIn(StateDuration.class, StateDurationMixIn.class)
        .addMixIn(ImmutableStateDuration.Builder.class, StateDurationMixIn.BuilderMixIn.class)
        .build();
  }

  @Provides
  @MixIn
  /* package */ static Serializer<Event> eventSerializer(@MixIn ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, Event.class);
  }

  @Provides
  @MixIn
  /* package */ static Deserializer<Event> eventDeserializer(@MixIn ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, Event.class);
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
  /* package */ static Serializer<StateDuration> stateDurationSerializer(
      @MixIn ObjectMapper mapper) {
    return new JsonSerializer<>(mapper, StateDuration.class);
  }

  @Provides
  @MixIn
  /* package */ static Deserializer<StateDuration> stateDurationDeserializer(
      @MixIn ObjectMapper mapper) {
    return new JsonDeserializer<>(mapper, StateDuration.class);
  }
}
