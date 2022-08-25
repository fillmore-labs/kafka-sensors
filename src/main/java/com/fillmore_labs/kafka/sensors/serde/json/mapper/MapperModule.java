package com.fillmore_labs.kafka.sensors.serde.json.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.ReadingJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.StateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
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
  /* package */ abstract BiMapper<Reading, ReadingJson> readingBiMapper(ReadingMapper mapper);

  @Binds
  /* package */ abstract BiMapper<SensorState, SensorStateJson> sensorStateBiMapper(
      SensorStateMapperImpl mapper);

  @Binds
  /* package */ abstract BiMapper<StateDuration, StateDurationJson> stateDurationBiMapper(
      StateDurationMapperImpl mapper);
}
