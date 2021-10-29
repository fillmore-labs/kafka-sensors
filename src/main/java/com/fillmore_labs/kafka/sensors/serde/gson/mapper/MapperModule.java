package com.fillmore_labs.kafka.sensors.serde.gson.mapper;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.EventGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.StateDurationGson;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public abstract class MapperModule {
  private MapperModule() {}

  @Provides
  @Singleton
  /* package */ static BiMapper<Event, EventGson> eventBiMapper() {
    return new EventMapperImpl();
  }

  @Provides
  @Singleton
  /* package */ static BiMapper<SensorState, SensorStateGson> sensorStateBiMapper() {
    return new SensorStateMapperImpl();
  }

  @Provides
  @Singleton
  /* package */ static BiMapper<StateDuration, StateDurationGson> stateDurationBiMapper() {
    return new StateDurationMapperImpl();
  }
}
