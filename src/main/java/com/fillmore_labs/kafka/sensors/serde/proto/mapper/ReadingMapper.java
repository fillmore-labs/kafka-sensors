package com.fillmore_labs.kafka.sensors.serde.proto.mapper;

import static org.mapstruct.MappingConstants.ANY_REMAINING;
import static org.mapstruct.MappingConstants.PREFIX_TRANSFORMATION;
import static org.mapstruct.MappingConstants.THROW_EXCEPTION;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.Reading.Position;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import com.google.protobuf.Timestamp;
import java.time.Instant;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.mapstruct.BeanMapping;
import org.mapstruct.EnumMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Immutable
@Mapper(config = MapStructConfig.class)
public abstract class ReadingMapper
    implements BiMapper<Reading, com.fillmore_labs.kafka.sensors.proto.v1.Reading> {
  protected static Instant mapTime(Timestamp timestamp) {
    return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
  }

  protected static Timestamp mapTime(Instant instant) {
    return Timestamp.newBuilder()
        .setSeconds(instant.getEpochSecond())
        .setNanos(instant.getNano())
        .build();
  }

  @Override
  @BeanMapping(unmappedTargetPolicy = IGNORE)
  public abstract com.fillmore_labs.kafka.sensors.proto.v1.@PolyNull Reading map(
      @PolyNull Reading model);

  @EnumMapping(nameTransformationStrategy = PREFIX_TRANSFORMATION, configuration = "POSITION_")
  protected abstract com.fillmore_labs.kafka.sensors.proto.v1.Reading.Position unmapPosition(
      Position position);

  @InheritInverseConfiguration
  @ValueMapping(source = ANY_REMAINING, target = THROW_EXCEPTION)
  protected abstract Position mapPosition(
      com.fillmore_labs.kafka.sensors.proto.v1.Reading.Position position);
}
