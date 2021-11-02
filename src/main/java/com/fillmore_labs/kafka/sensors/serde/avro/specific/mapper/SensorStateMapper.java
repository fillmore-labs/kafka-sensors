package com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Immutable
@Mapper(config = MapStructConfig.class, uses = ReadingMapper.class)
/* package */ abstract class SensorStateMapper
    implements BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> {
  @Override
  @Mapping(ignore = true, target = "readingBuilder")
  public abstract com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorState map(
      @PolyNull SensorState model);
}
