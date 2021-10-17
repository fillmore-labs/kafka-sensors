package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateDurationReflect;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;

@Immutable
@Mapper(
    config = MapStructConfig.class,
    uses = com.fillmore_labs.kafka.sensors.serde.confluent.reflect.mapper.SensorStateMapper.class)
/* package */ abstract class SensorStateDurationMapper
    implements BiMapper<SensorStateDuration, SensorStateDurationReflect> {
  @Override
  public abstract @PolyNull SensorStateDurationReflect map(@PolyNull SensorStateDuration model);

  @Override
  public abstract @PolyNull SensorStateDuration unmap(@PolyNull SensorStateDurationReflect data);
}
