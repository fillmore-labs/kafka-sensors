package com.fillmore_labs.kafka.sensors.serde.proto.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import java.time.Duration;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = ReadingMapper.class)
/* package */ abstract class StateDurationMapper
    implements BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.proto.v1.StateDuration> {
  protected static Duration unmapDuration(com.google.protobuf.Duration data) {
    return Duration.ofSeconds(data.getSeconds(), data.getNanos());
  }

  protected static com.google.protobuf.Duration mapDuration(Duration model) {
    return com.google.protobuf.Duration.newBuilder()
        .setSeconds(model.getSeconds())
        .setNanos(model.getNano())
        .build();
  }

  @Override
  @BeanMapping(unmappedTargetPolicy = IGNORE)
  public abstract com.fillmore_labs.kafka.sensors.proto.v1.@PolyNull StateDuration map(
      @PolyNull StateDuration model);
}
