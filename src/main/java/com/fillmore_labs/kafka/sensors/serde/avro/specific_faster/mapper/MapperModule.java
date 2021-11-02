package com.fillmore_labs.kafka.sensors.serde.avro.specific_faster.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
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
  /* package */ static ReadingMapper readingMapper() {
    return new ReadingMapperImpl();
  }

  @Binds
  @Named("faster")
  /* package */ abstract BiMapper<Reading, com.fillmore_labs.kafka.sensors.avro.Reading>
      readingBiMapper(ReadingMapper mapper);

  @Binds
  @Named("faster")
  /* package */ abstract BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState>
      sensorStateBiMapper(SensorStateMapperImpl mapper);

  @Binds
  @Named("faster")
  /* package */ abstract BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.avro.StateDuration>
      stateDurationBiMapper(StateDurationMapperImpl mapper);
}
