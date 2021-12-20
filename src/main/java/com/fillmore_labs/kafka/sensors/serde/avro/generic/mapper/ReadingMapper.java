package com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
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
  private final PositionMapper positionMapper;

  @Inject
  /* package */ ReadingMapper(PositionMapper positionMapper) {
    this.positionMapper = positionMapper;
  }

  @Override
  public @PolyNull GenericRecord map(@PolyNull Reading model) {
    if (model == null) {
      return null;
    }
    return new GenericRecordBuilder(ReadingSchema.SCHEMA)
        .set(ReadingSchema.FIELD_TIME, model.getTime())
        .set(ReadingSchema.FIELD_POSITION, positionMapper.map(model.getPosition()))
        .build();
  }

  @Override
  public @PolyNull Reading unmap(@PolyNull GenericRecord data) {
    if (data == null) {
      return null;
    }
    return Reading.builder()
        .time((Instant) data.get(ReadingSchema.FIELD_TIME))
        .position(positionMapper.unmap(data.get(ReadingSchema.FIELD_POSITION)))
        .build();
  }
}
