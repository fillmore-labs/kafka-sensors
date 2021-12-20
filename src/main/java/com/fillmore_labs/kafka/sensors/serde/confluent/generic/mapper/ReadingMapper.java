package com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper.PositionMapper;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.ReadingSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.InstantNanosHelper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
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
        .set(ReadingSchema.FIELD_TIME, InstantNanosHelper.instant2Nanos(model.getTime()))
        .set(ReadingSchema.FIELD_POSITION, positionMapper.map(model.getPosition()))
        .build();
  }

  @Override
  public @PolyNull Reading unmap(@PolyNull GenericRecord data) {
    if (data == null) {
      return null;
    }
    return Reading.builder()
        .time(InstantNanosHelper.nanos2Instant((Long) data.get(ReadingSchema.FIELD_TIME)))
        .position(positionMapper.unmap(data.get(ReadingSchema.FIELD_POSITION)))
        .build();
  }
}
