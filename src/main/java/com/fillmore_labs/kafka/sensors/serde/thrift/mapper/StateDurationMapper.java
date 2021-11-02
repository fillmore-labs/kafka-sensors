package com.fillmore_labs.kafka.sensors.serde.thrift.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.mapping.TimeNanoMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Immutable
@Mapper(
    config = MapStructConfig.class,
    uses = {ReadingMapper.class, TimeNanoMapper.class})
public abstract class StateDurationMapper
    implements BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration> {
  @Override
  @BeanMapping(unmappedTargetPolicy = IGNORE)
  public abstract com.fillmore_labs.kafka.sensors.thrift.v1.@PolyNull StateDuration map(
      @PolyNull StateDuration model);
}
