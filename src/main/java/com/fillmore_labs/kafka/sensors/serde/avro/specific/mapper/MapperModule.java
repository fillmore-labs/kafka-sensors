package com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
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
  /* package */ abstract BiMapper<Event, com.fillmore_labs.kafka.sensors.avro.Event> eventBiMapper(
      EventMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState>
      sensorStateBiMapper(SensorStateMapperImpl mapper);

  @Binds
  /* package */ abstract BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.avro.StateDuration>
      stateDurationBiMapper(StateDurationMapperImpl mapper);
}
