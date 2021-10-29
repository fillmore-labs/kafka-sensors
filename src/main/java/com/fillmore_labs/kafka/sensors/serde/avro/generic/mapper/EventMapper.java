package com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.EventSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.PositionSchema;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import javax.inject.Inject;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.checkerframework.checker.nullness.qual.PolyNull;

@Immutable
public final class EventMapper implements BiMapper<Event, GenericRecord> {
  @Inject
  /* package */ EventMapper() {}

  private static GenericEnumSymbol<?> mapPosition(Event.Position model) {
    // Checkstyle ignore MissingSwitchDefault
    switch (model) {
      case OFF:
        return PositionSchema.ENUM_OFF;

      case ON:
        return PositionSchema.ENUM_ON;
    }

    throw new IllegalArgumentException("Unexpected Position Enum: " + model);
  }

  private static Event.Position unmapPosition(GenericEnumSymbol<?> data) {
    switch (data.toString()) {
      case PositionSchema.POSITION_OFF:
        return Event.Position.OFF;

      case PositionSchema.POSITION_ON:
        return Event.Position.ON;

      default:
        throw new IllegalArgumentException("Unexpected Enum value: " + data);
    }
  }

  @Override
  public @PolyNull GenericRecord map(@PolyNull Event model) {
    if (model == null) {
      return null;
    }
    return new GenericRecordBuilder(EventSchema.SCHEMA)
        .set(EventSchema.FIELD_TIME, model.getTime())
        .set(EventSchema.FIELD_POSITION, mapPosition(model.getPosition()))
        .build();
  }

  @Override
  public @PolyNull Event unmap(@PolyNull GenericRecord data) {
    if (data == null) {
      return null;
    }
    return Event.builder()
        .time((Instant) data.get(EventSchema.FIELD_TIME))
        .position(unmapPosition((GenericEnumSymbol<?>) data.get(EventSchema.FIELD_POSITION)))
        .build();
  }
}
