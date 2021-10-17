package com.fillmore_labs.kafka.sensors.serde.gson.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.converter.DurationDecimalHelper;
import com.fillmore_labs.kafka.sensors.serde.converter.InstantDecimalHelper;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateDurationGson;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Immutable
@Mapper(
    config = MapStructConfig.class,
    uses = {InstantDecimalHelper.class, DurationDecimalHelper.class})
/* package */ abstract class SensorStateDurationMapper
    implements BiMapper<SensorStateDuration, SensorStateDurationGson> {
  @Override
  @Mapping(source = "event", target = ".")
  public abstract @PolyNull SensorStateDurationGson map(@PolyNull SensorStateDuration model);

  @Override
  @InheritInverseConfiguration
  public abstract @PolyNull SensorStateDuration unmap(@PolyNull SensorStateDurationGson data);
}
