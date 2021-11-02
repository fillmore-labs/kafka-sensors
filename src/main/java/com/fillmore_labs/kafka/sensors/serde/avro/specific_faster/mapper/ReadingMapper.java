package com.fillmore_labs.kafka.sensors.serde.avro.specific_faster.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.mapping.TimeNanoMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = TimeNanoMapper.class)
/* package */ abstract class ReadingMapper
    implements BiMapper<Reading, com.fillmore_labs.kafka.sensors.avro.Reading> {
  @Override
  @BeanMapping(builder = @Builder(disableBuilder = true))
  public abstract com.fillmore_labs.kafka.sensors.avro.@PolyNull Reading map(
      @PolyNull Reading model);
}
