package com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateStateSchema;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.checkerframework.checker.nullness.qual.PolyNull;

@Immutable
/* package */ final class SensorStateMapper implements BiMapper<SensorState, GenericRecord> {
  @Inject
  /* package */ SensorStateMapper() {}

  private static GenericEnumSymbol<?> stateMap(SensorState.State model) {
    // Checkstyle ignore MissingSwitchDefault
    switch (model) {
      case OFF:
        return SensorStateStateSchema.ENUM_OFF;

      case ON:
        return SensorStateStateSchema.ENUM_ON;
    }

    throw new IllegalArgumentException("Unexpected State Enum: " + model);
  }

  private static SensorState.State stateUnmap(GenericEnumSymbol<?> data) {
    switch (data.toString()) {
      case SensorStateStateSchema.STATE_OFF:
        return SensorState.State.OFF;

      case SensorStateStateSchema.STATE_ON:
        return SensorState.State.ON;

      default:
        throw new IllegalArgumentException("Unexpected Enum value: " + data);
    }
  }

  @Override
  public @PolyNull GenericRecord map(@PolyNull SensorState model) {
    if (model == null) {
      return null;
    }
    return new GenericRecordBuilder(SensorStateSchema.SCHEMA)
        .set(SensorStateSchema.FIELD_ID, model.getId())
        .set(SensorStateSchema.FIELD_TIME, model.getTime())
        .set(SensorStateSchema.FIELD_STATE, stateMap(model.getState()))
        .build();
  }

  @Override
  public @PolyNull SensorState unmap(@PolyNull GenericRecord data) {
    if (data == null) {
      return null;
    }
    return SensorState.builder()
        .id((String) data.get(SensorStateSchema.FIELD_ID))
        .time((Instant) data.get(SensorStateSchema.FIELD_TIME))
        .state(stateUnmap((GenericEnumSymbol<?>) data.get(SensorStateSchema.FIELD_STATE)))
        .build();
  }
}
