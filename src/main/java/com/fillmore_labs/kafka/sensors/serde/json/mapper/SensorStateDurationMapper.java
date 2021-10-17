package com.fillmore_labs.kafka.sensors.serde.json.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = SensorStateMapper.class)
public abstract class SensorStateDurationMapper
    implements BiMapper<SensorStateDuration, SensorStateDurationJson> {
  @Override
  public abstract @PolyNull SensorStateDurationJson map(@PolyNull SensorStateDuration model);

  @Override
  public abstract @PolyNull SensorStateDuration unmap(@PolyNull SensorStateDurationJson data);
}
