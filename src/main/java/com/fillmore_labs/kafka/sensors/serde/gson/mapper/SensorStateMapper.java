package com.fillmore_labs.kafka.sensors.serde.gson.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.converter.InstantDecimalHelper;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = InstantDecimalHelper.class)
/* package */ abstract class SensorStateMapper implements BiMapper<SensorState, SensorStateGson> {
  @Override
  public abstract @PolyNull SensorStateGson map(@PolyNull SensorState model);

  @Override
  public abstract @PolyNull SensorState unmap(@PolyNull SensorStateGson data);
}
