package com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = DurationMapper.class)
/* package */ abstract class SensorStateMapper
    implements BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> {
  @Override
  public abstract com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorState map(
      @PolyNull SensorState sensorState);

  @Override
  public abstract @PolyNull SensorState unmap(
      com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorState sensorState);
}
