package com.fillmore_labs.kafka.sensors.serde.proto.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.proto.serialization.ProtoTypesMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import javax.inject.Inject;
import org.checkerframework.checker.nullness.qual.PolyNull;

@Immutable
/* package */ final class SensorStateDurationMapper
    implements BiMapper<
        SensorStateDuration, com.fillmore_labs.kafka.sensors.v1.SensorStateDuration> {
  private final BiMapper<SensorState, com.fillmore_labs.kafka.sensors.v1.SensorState>
      sensorStateMapper;

  @Inject
  /* package */ SensorStateDurationMapper(
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.v1.SensorState> sensorStateMapper) {
    this.sensorStateMapper = sensorStateMapper;
  }

  @Override
  public com.fillmore_labs.kafka.sensors.v1.@PolyNull SensorStateDuration map(
      @PolyNull SensorStateDuration model) {
    if (model == null) {
      return null;
    }

    return com.fillmore_labs.kafka.sensors.v1.SensorStateDuration.newBuilder()
        .setEvent(sensorStateMapper.map(model.getEvent()))
        .setDuration(ProtoTypesMapper.duration2Duration(model.getDuration()))
        .build();
  }

  @Override
  public @PolyNull SensorStateDuration unmap(
      com.fillmore_labs.kafka.sensors.v1.@PolyNull SensorStateDuration data) {
    if (data == null) {
      return null;
    }
    return SensorStateDuration.builder()
        .event(sensorStateMapper.unmap(data.getEvent()))
        .duration(ProtoTypesMapper.duration2Duration(data.getDuration()))
        .build();
  }
}
