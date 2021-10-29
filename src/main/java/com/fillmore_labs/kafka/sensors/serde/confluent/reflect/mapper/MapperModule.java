package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.mapper;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.EventReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.StateDurationReflect;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public abstract class MapperModule {
  private MapperModule() {}

  @Provides
  @Singleton
  /* package */ static EventMapper eventMapper() {
    return new EventMapperImpl();
  }

  @Binds
  /* package */ abstract BiMapper<Event, EventReflect> eventBiMapper(EventMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorState, SensorStateReflect> sensorStateBiMapper(
      SensorStateMapperImpl mapper);

  @Binds
  /* package */ abstract BiMapper<StateDuration, StateDurationReflect> stateDurationBiMapper(
      StateDurationMapperImpl mapper);
}
