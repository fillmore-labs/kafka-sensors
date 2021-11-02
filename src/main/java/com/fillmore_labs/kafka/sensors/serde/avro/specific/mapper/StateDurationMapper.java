package com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.mapping.TimeNanoMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Immutable
@Mapper(
    config = MapStructConfig.class,
    uses = {ReadingMapper.class, TimeNanoMapper.class})
/* package */ abstract class StateDurationMapper
    implements BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.avro.StateDuration> {
  @Override
  @Mapping(ignore = true, target = "readingBuilder")
  public abstract com.fillmore_labs.kafka.sensors.avro.@PolyNull StateDuration map(
      @PolyNull StateDuration model);
}
