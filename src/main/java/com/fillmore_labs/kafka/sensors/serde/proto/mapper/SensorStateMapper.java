package com.fillmore_labs.kafka.sensors.serde.proto.mapper;

import static org.mapstruct.MappingConstants.ANY_REMAINING;
import static org.mapstruct.MappingConstants.THROW_EXCEPTION;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorState.State;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.proto.serialization.ProtoTypesMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Immutable
@Mapper(config = MapStructConfig.class, uses = ProtoTypesMapper.class)
/* package */ abstract class SensorStateMapper
    implements BiMapper<SensorState, com.fillmore_labs.kafka.sensors.v1.SensorState> {

  @Override
  public abstract com.fillmore_labs.kafka.sensors.v1.@PolyNull SensorState map(
      @PolyNull SensorState model);

  @Override
  public final @PolyNull SensorState unmap(
      com.fillmore_labs.kafka.sensors.v1.@PolyNull SensorState data) {
    if (data == null) {
      return null;
    }
    return SensorState.builder()
        .id(data.getId())
        .state(mapState(data.getState()))
        .time(ProtoTypesMapper.timestamp2Instant(data.getTime()))
        .build();
  }

  @InheritInverseConfiguration
  @ValueMapping(source = ANY_REMAINING, target = THROW_EXCEPTION)
  protected abstract State mapState(com.fillmore_labs.kafka.sensors.v1.SensorState.State state);

  @ValueMappings({
    @ValueMapping(source = "OFF", target = "STATE_OFF"),
    @ValueMapping(source = "ON", target = "STATE_ON"),
  })
  protected abstract com.fillmore_labs.kafka.sensors.v1.SensorState.State unmapState(State state);
}
