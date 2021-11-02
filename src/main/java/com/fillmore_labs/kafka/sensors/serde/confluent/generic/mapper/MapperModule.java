package com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization.Confluent;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;
import org.apache.avro.generic.GenericRecord;

@Module
public abstract class MapperModule {
  private MapperModule() {}

  @Binds
  @Singleton
  @Confluent
  /* package */ abstract BiMapper<Reading, GenericRecord> readingBiMapper(ReadingMapper mapper);

  @Binds
  @Singleton
  @Confluent
  /* package */ abstract BiMapper<SensorState, GenericRecord> sensorStateBiMapper(
      SensorStateMapper mapper);

  @Binds
  @Singleton
  @Confluent
  /* package */ abstract BiMapper<StateDuration, GenericRecord> stateDurationBiMapper(
      StateDurationMapper mapper);
}
