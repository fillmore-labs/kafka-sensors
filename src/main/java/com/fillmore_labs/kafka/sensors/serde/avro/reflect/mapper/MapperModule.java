package com.fillmore_labs.kafka.sensors.serde.avro.reflect.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SensorStateDurationReflect;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SensorStateReflect;
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
  /* package */ abstract BiMapper<SensorState, SensorStateReflect> sensorStateBiMapper(
      SensorStateMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorStateDuration, SensorStateDurationReflect>
      sensorStateDurationBiMapper(SensorStateDurationMapperImpl mapper);
}
