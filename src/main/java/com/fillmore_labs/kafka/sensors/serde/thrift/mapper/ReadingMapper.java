package com.fillmore_labs.kafka.sensors.serde.thrift.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.mapping.TimeNanoMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = TimeNanoMapper.class)
public abstract class ReadingMapper
    implements BiMapper<Reading, com.fillmore_labs.kafka.sensors.thrift.v1.Reading> {
  @Override
  @BeanMapping(unmappedTargetPolicy = IGNORE)
  public abstract com.fillmore_labs.kafka.sensors.thrift.v1.@PolyNull Reading map(
      @PolyNull Reading model);
}
