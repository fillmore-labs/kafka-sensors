package com.fillmore_labs.kafka.sensors.serde.gson.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateDurationGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public abstract class GsonMapperModule {
  private GsonMapperModule() {}

  @Provides
  @Singleton
  /* package */ static BiMapper<SensorState, SensorStateGson> sensorStateBiMapper() {
    return new SensorStateMapperImpl();
  }

  @Provides
  @Singleton
  /* package */ static BiMapper<SensorStateDuration, SensorStateDurationGson>
      sensorStateDurationBiMapper() {
    return new SensorStateDurationMapperImpl();
  }
}
