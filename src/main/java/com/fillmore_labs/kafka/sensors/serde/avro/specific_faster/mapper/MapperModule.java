package com.fillmore_labs.kafka.sensors.serde.avro.specific_faster.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
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
  @Named("faster")
  /* package */ abstract BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState>
      sensorStateDirectBiMapper(SensorStateMapper mapper);

  @Binds
  @Named("faster")
  /* package */ abstract BiMapper<
          SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration>
      sensorStateDurationDirectBiMapper(SensorStateDurationMapper mapper);

  @Binds
  /* package */ abstract SensorStateDurationMapper sensorStateDurationMapper(
      SensorStateDurationMapperImpl mapper);
}
