package com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.PositionSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.ReadingSchema;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.checkerframework.checker.nullness.qual.PolyNull;

@Immutable
public final class ReadingMapper implements BiMapper<Reading, GenericRecord> {
  @Inject
  /* package */ ReadingMapper() {}

  @SuppressWarnings({"nullness:return", "UnnecessaryParentheses"})
  private static Object mapPosition(Reading.Position model) {
    return switch (model) {
      case OFF -> PositionSchema.ENUM_OFF;
      case ON -> PositionSchema.ENUM_ON;
    };
  }

  @SuppressWarnings({"nullness:return", "UnnecessaryParentheses"})
  private static Reading.Position unmapPosition(Object data) {
    return switch (data.toString()) {
      case PositionSchema.POSITION_OFF -> Reading.Position.OFF;
      case PositionSchema.POSITION_ON -> Reading.Position.ON;
      default -> throw new IllegalArgumentException("Unexpected Enum value: " + data);
    };
  }

  @Override
  public @PolyNull GenericRecord map(@PolyNull Reading model) {
    if (model == null) {
      return null;
    }
    return new GenericRecordBuilder(ReadingSchema.SCHEMA)
        .set(ReadingSchema.FIELD_TIME, model.getTime())
        .set(ReadingSchema.FIELD_POSITION, mapPosition(model.getPosition()))
        .build();
  }

  @Override
  public @PolyNull Reading unmap(@PolyNull GenericRecord data) {
    if (data == null) {
      return null;
    }
    return Reading.builder()
        .time((Instant) data.get(ReadingSchema.FIELD_TIME))
        .position(unmapPosition(data.get(ReadingSchema.FIELD_POSITION)))
        .build();
  }
}
