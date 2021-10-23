package com.fillmore_labs.kafka.sensors.serde.avro.specific_faster.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper.DurationMapper;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Immutable
@Mapper(config = MapStructConfig.class, uses = DurationMapper.class)
/* package */ abstract class SensorStateMapper
    implements BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> {
  @Override
  public final com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorState map(
      @PolyNull SensorState model) {
    if (model == null) {
      return null;
    }
    var data = new com.fillmore_labs.kafka.sensors.avro.SensorState();
    mapto(model, data);
    return data;
  }

  protected abstract void mapto(
      @PolyNull SensorState model,
      @MappingTarget com.fillmore_labs.kafka.sensors.avro.SensorState data);

  @Override
  public abstract @PolyNull SensorState unmap(
      com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorState data);
}
