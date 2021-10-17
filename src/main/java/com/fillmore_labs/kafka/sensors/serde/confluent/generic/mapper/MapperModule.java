package com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;
import org.apache.avro.generic.GenericRecord;

@Module
public abstract class MapperModule {
  private MapperModule() {}

  @Binds
  @Confluent
  @Singleton
  /* package */ abstract BiMapper<SensorState, GenericRecord> sensorStateBiMapper(
      SensorStateMapper mapper);

  @Binds
  @Confluent
  @Singleton
  /* package */ abstract BiMapper<SensorStateDuration, GenericRecord> sensorStateDurationBiMapper(
      SensorStateDurationMapper mapper);
}
