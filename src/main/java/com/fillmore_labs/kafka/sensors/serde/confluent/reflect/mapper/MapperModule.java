package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.ReadingReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.StateDurationReflect;
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
  /* package */ abstract BiMapper<Reading, ReadingReflect> readingBiMapper(ReadingMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorState, SensorStateReflect> sensorStateBiMapper(
      SensorStateMapperImpl mapper);

  @Binds
  /* package */ abstract BiMapper<StateDuration, StateDurationReflect> stateDurationBiMapper(
      StateDurationMapperImpl mapper);
}
