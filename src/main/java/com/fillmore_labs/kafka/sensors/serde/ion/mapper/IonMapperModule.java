package com.fillmore_labs.kafka.sensors.serde.ion.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateDurationIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateIon;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public abstract class IonMapperModule {
  private IonMapperModule() {}

  @Provides
  @Singleton
  /* package */ static SensorStateMapper sensorStateMapper() {
    return new SensorStateMapperImpl();
  }

  @Binds
  /* package */ abstract BiMapper<SensorState, SensorStateIon> sensorStateBiMapper(
      SensorStateMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorStateDuration, SensorStateDurationIon>
      sensorStateDurationBiMapper(SensorStateDurationMapper mapper);

  @Binds
  /* package */ abstract SensorStateDurationMapper sensorStateDurationMapper(
      SensorStateDurationMapperImpl mapper);
}
