package com.fillmore_labs.kafka.sensors.serde.avro.reflect.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SensorStateReflect;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class)
/* package */ abstract class SensorStateMapper
    implements BiMapper<SensorState, SensorStateReflect> {
  @Override
  public abstract @PolyNull SensorStateReflect map(@PolyNull SensorState model);

  @Override
  public abstract @PolyNull SensorState unmap(@PolyNull SensorStateReflect data);
}
