package com.fillmore_labs.kafka.sensors.serde.gson.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.converter.InstantDecimalHelper;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Immutable
@Mapper(config = MapStructConfig.class, uses = InstantDecimalHelper.class)
/* package */ abstract class SensorStateMapper implements BiMapper<SensorState, SensorStateGson> {
  @Override
  @Mapping(source = "reading", target = ".")
  public abstract @PolyNull SensorStateGson map(@PolyNull SensorState model);

  @Override
  @InheritInverseConfiguration
  public abstract @PolyNull SensorState unmap(@PolyNull SensorStateGson data);
}
