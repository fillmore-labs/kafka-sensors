package com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper;

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

  @Provides
  @Singleton
  /* package */ static SensorStateDirectMapper sensorStateDirectMapper() {
    return new SensorStateDirectMapperImpl();
  }

  @Binds
  /* package */ abstract BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState>
      sensorStateBiMapper(SensorStateMapper mapper);

  @Binds
  @Named("direct")
  /* package */ abstract BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState>
      sensorStateDirectBiMapper(SensorStateDirectMapper mapper);

  @Binds
  /* package */ abstract BiMapper<
          SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration>
      sensorStateDurationBiMapper(SensorStateDurationMapper mapper);

  @Binds
  @Named("direct")
  /* package */ abstract BiMapper<
          SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration>
      sensorStateDurationDirectBiMapper(SensorStateDurationDirectMapper mapper);

  @Binds
  /* package */ abstract SensorStateDurationMapper sensorStateDurationMapper(
      SensorStateDurationMapperImpl mapper);

  @Binds
  /* package */ abstract SensorStateDurationDirectMapper sensorStateDurationDirectMapper(
      SensorStateDurationDirectMapperImpl mapper);
}
