package com.fillmore_labs.kafka.sensors.serde.proto.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public abstract class ProtoMapperModule {
  private ProtoMapperModule() {}

  @Provides
  @Singleton
  /* package */ static BiMapper<SensorState, com.fillmore_labs.kafka.sensors.v1.SensorState>
      sensorStateBiMapper() {
    return new SensorStateMapperImpl();
  }

  @Binds
  /* package */ abstract BiMapper<
          SensorStateDuration, com.fillmore_labs.kafka.sensors.v1.SensorStateDuration>
      sensorStateDurationBiMapper(SensorStateDurationMapper mapper);
}
