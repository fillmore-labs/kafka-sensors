package com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import javax.inject.Inject;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.checkerframework.checker.nullness.qual.PolyNull;

@Immutable
/* package */ final class SensorStateMapper implements BiMapper<SensorState, GenericRecord> {
  private final BiMapper<Reading, GenericRecord> readingMapper;

  @Inject
  /* package */ SensorStateMapper(ReadingMapper readingMapper) {
    this.readingMapper = readingMapper;
  }

  @Override
  public @PolyNull GenericRecord map(@PolyNull SensorState model) {
    if (model == null) {
      return null;
    }
    return new GenericRecordBuilder(SensorStateSchema.SCHEMA)
        .set(SensorStateSchema.FIELD_ID, model.getId())
        .set(SensorStateSchema.FIELD_READING, readingMapper.map(model.getReading()))
        .build();
  }

  @Override
  public @PolyNull SensorState unmap(@PolyNull GenericRecord data) {
    if (data == null) {
      return null;
    }
    return SensorState.builder()
        .id((String) data.get(SensorStateSchema.FIELD_ID))
        .reading(readingMapper.unmap((GenericRecord) data.get(SensorStateSchema.FIELD_READING)))
        .build();
  }
}
