package com.fillmore_labs.kafka.sensors.serde.avro.specific_faster.mapper;

import com.fillmore_labs.kafka.sensors.model.Event;
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
/* package */ abstract class EventMapper
    implements BiMapper<Event, com.fillmore_labs.kafka.sensors.avro.Event> {
  @Override
  @BeanMapping(builder = @Builder(disableBuilder = true))
  public abstract com.fillmore_labs.kafka.sensors.avro.@PolyNull Event map(@PolyNull Event model);
}
