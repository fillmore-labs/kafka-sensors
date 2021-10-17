package com.fillmore_labs.kafka.sensors.serde.ion.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateIon;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class)
/* package */ abstract class SensorStateMapper implements BiMapper<SensorState, SensorStateIon> {
  @Override
  public abstract @PolyNull SensorStateIon map(@PolyNull SensorState sensorState);

  @Override
  public abstract @PolyNull SensorState unmap(@PolyNull SensorStateIon sensorState);
}
