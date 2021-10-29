package com.fillmore_labs.kafka.sensors.serde.ion.mapper;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.EventIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.StateDurationIon;
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
  /* package */ abstract BiMapper<Event, EventIon> eventBiMapper(EventMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorState, SensorStateIon> sensorStateBiMapper(
      SensorStateMapperImpl mapper);

  @Binds
  /* package */ abstract BiMapper<StateDuration, StateDurationIon> stateDurationBiMapper(
      StateDurationMapperImpl mapper);
}
