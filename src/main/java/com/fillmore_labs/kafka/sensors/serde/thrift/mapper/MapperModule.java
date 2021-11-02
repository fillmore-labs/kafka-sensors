package com.fillmore_labs.kafka.sensors.serde.thrift.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
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
  /* package */ static ReadingMapper readingMapper() {
    return new ReadingMapperImpl();
  }

  @Binds
  /* package */ abstract BiMapper<Reading, com.fillmore_labs.kafka.sensors.thrift.v1.Reading>
      readingBiMapper(ReadingMapper mapper);

  @Binds
  /* package */ abstract BiMapper<
          SensorState, com.fillmore_labs.kafka.sensors.thrift.v1.SensorState>
      sensorStateBiMapper(SensorStateMapperImpl mapper);

  @Binds
  /* package */ abstract BiMapper<
          StateDuration, com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration>
      stateDurationBiMapper(StateDurationMapperImpl mapper);
}
