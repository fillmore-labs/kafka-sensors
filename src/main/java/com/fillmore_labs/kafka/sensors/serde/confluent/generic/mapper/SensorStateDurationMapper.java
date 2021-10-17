package com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateDurationSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationMicroHelper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import javax.inject.Inject;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.checkerframework.checker.nullness.qual.PolyNull;

@Immutable
/* package */ final class SensorStateDurationMapper
    implements BiMapper<SensorStateDuration, GenericRecord> {
  private final BiMapper<SensorState, GenericRecord> mapper;

  @Inject
  /* package */ SensorStateDurationMapper(SensorStateMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public @PolyNull GenericRecord map(@PolyNull SensorStateDuration model) {
    if (model == null) {
      return null;
    }
    return new GenericRecordBuilder(SensorStateDurationSchema.SCHEMA)
        .set(SensorStateDurationSchema.FIELD_EVENT, mapper.map(model.getEvent()))
        .set(
            SensorStateDurationSchema.FIELD_DURATION,
            DurationMicroHelper.duration2Micros(model.getDuration()))
        .build();
  }

  @Override
  public @PolyNull SensorStateDuration unmap(@PolyNull GenericRecord data) {
    if (data == null) {
      return null;
    }
    return SensorStateDuration.builder()
        .event(mapper.unmap((GenericRecord) data.get(SensorStateDurationSchema.FIELD_EVENT)))
        .duration(
            DurationMicroHelper.micros2Duration(
                (Long) data.get(SensorStateDurationSchema.FIELD_DURATION)))
        .build();
  }
}
