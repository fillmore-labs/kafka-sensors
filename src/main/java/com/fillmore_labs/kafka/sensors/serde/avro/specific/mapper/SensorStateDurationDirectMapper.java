package com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Immutable
@Mapper(
    config = MapStructConfig.class,
    uses = {DurationMapper.class, SensorStateDirectMapper.class})
/* package */ abstract class SensorStateDurationDirectMapper
    implements BiMapper<
        SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> {
  @Override
  public final com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorStateDuration map(
      @PolyNull SensorStateDuration model) {
    if (model == null) {
      return null;
    }
    var data = new com.fillmore_labs.kafka.sensors.avro.SensorStateDuration();
    mapto(model, data);
    return data;
  }

  @Override
  public abstract @PolyNull SensorStateDuration unmap(
      com.fillmore_labs.kafka.sensors.avro.@PolyNull SensorStateDuration sensorState);

  protected abstract void mapto(
      @PolyNull SensorStateDuration model,
      @MappingTarget com.fillmore_labs.kafka.sensors.avro.SensorStateDuration data);
}
