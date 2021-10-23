package com.fillmore_labs.kafka.sensors.serde.json.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
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
  /* package */ static SensorStateMapper sensorStateMapper() {
    return new SensorStateMapperImpl();
  }

  @Binds
  /* package */ abstract BiMapper<SensorState, SensorStateJson> sensorStateBiMapper(
      SensorStateMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorStateDuration, SensorStateDurationJson>
      sensorStateDurationBiMapper(SensorStateDurationMapper mapper);

  @Binds
  /* package */ abstract SensorStateDurationMapper sensorStateDurationMapper(
      SensorStateDurationMapperImpl mapper);
}
