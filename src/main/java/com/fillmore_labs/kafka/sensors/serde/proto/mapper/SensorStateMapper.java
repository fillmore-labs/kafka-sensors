package com.fillmore_labs.kafka.sensors.serde.proto.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = ReadingMapper.class)
/* package */ abstract class SensorStateMapper
    implements BiMapper<SensorState, com.fillmore_labs.kafka.sensors.proto.v1.SensorState> {

  @Override
  @BeanMapping(unmappedTargetPolicy = IGNORE)
  public abstract com.fillmore_labs.kafka.sensors.proto.v1.@PolyNull SensorState map(
      @PolyNull SensorState model);
}
