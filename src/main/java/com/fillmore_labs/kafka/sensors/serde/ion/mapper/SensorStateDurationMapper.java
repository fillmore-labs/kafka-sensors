package com.fillmore_labs.kafka.sensors.serde.ion.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateDurationIon;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = SensorStateMapper.class)
/* package */ abstract class SensorStateDurationMapper
    implements BiMapper<SensorStateDuration, SensorStateDurationIon> {
  @Override
  public abstract @PolyNull SensorStateDurationIon map(@PolyNull SensorStateDuration sensorState);

  @Override
  public abstract @PolyNull SensorStateDuration unmap(@PolyNull SensorStateDurationIon sensorState);
}
