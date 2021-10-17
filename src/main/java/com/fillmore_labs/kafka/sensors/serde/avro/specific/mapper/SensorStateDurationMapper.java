package com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Immutable
@Mapper(
    config = MapStructConfig.class,
    uses = {DurationMapper.class, SensorStateMapper.class})
/* package */ abstract class SensorStateDurationMapper
    implements BiMapper<
        SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> {
  @Override
  @Mapping(ignore = true, target = "eventBuilder")
  public abstract com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorStateDuration map(
      @PolyNull SensorStateDuration sensorState);

  @Override
  public abstract @PolyNull SensorStateDuration unmap(
      com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorStateDuration sensorState);
}
