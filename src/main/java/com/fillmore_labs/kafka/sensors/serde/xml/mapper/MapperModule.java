package com.fillmore_labs.kafka.sensors.serde.xml.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.ReadingXml;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.SensorStateXml;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.StateDurationXml;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import jakarta.inject.Singleton;

@Module
public abstract class MapperModule {
  private MapperModule() {}

  @Provides
  @Singleton
  /* package */ static ReadingMapper readingMapper() {
    return new ReadingMapperImpl();
  }

  @Binds
  /* package */ abstract BiMapper<Reading, ReadingXml> readingBiMapper(ReadingMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorState, SensorStateXml> sensorStateBiMapper(
      SensorStateMapperImpl mapper);

  @Binds
  /* package */ abstract BiMapper<StateDuration, StateDurationXml> stateDurationBiMapper(
      StateDurationMapperImpl mapper);
}
