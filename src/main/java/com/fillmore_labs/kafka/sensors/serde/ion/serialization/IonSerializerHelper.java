package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import java.io.IOException;

public final class IonSerializerHelper {
  private static final String FIELD_ID = "id";
  private static final String FIELD_POSITION = "position";
  private static final String FIELD_TIME = "time";
  private static final String FIELD_READING = "reading";
  private static final String FIELD_DURATION = "duration";

  private IonSerializerHelper() {}

  public static void serializeReading(IonWriter writer, ReadingIon message) throws IOException {
    writer.stepIn(IonType.STRUCT);
    writer.setFieldName(FIELD_TIME);
    writer.writeInt(message.getTime());
    writer.setFieldName(FIELD_POSITION);
    writer.writeSymbol(message.getPosition().name());
    writer.stepOut();
  }

  public static void serializeSensorState(IonWriter writer, SensorStateIon message)
      throws IOException {
    writer.stepIn(IonType.STRUCT);
    writer.setFieldName(FIELD_ID);
    writer.writeString(message.getId());
    writer.setFieldName(FIELD_READING);
    serializeReading(writer, message.getReading());
    writer.stepOut();
  }

  public static void serializeStateDuration(IonWriter writer, StateDurationIon message)
      throws IOException {
    writer.stepIn(IonType.STRUCT);
    writer.setFieldName(FIELD_ID);
    writer.writeString(message.getId());
    writer.setFieldName(FIELD_READING);
    serializeReading(writer, message.getReading());
    writer.setFieldName(FIELD_DURATION);
    writer.writeInt(message.getDuration());
    writer.stepOut();
  }

  public static ReadingIon deserializeReading(IonReader reader) {
    reader.next();
    return deserializeReadingInternal(reader);
  }

  @SuppressWarnings({"SwitchDefault", "PMD.SwitchStmtsShouldHaveDefault"})
  private static ReadingIon deserializeReadingInternal(IonReader reader) {
    var builder = ReadingIon.builder();

    reader.stepIn();
    for (var type = reader.next(); type != null; type = reader.next()) {
      var name = reader.getFieldName();
      switch (name) {
        case FIELD_TIME -> builder.time(reader.longValue());
        case FIELD_POSITION -> builder.position(
            ReadingIon.Position.valueOf(reader.symbolValue().getText()));
        default -> {} // Ignore unknown fields
      }
    }
    reader.stepOut();

    return builder.build();
  }

  @SuppressWarnings({"SwitchDefault", "PMD.SwitchStmtsShouldHaveDefault"})
  public static SensorStateIon deserializeSensorState(IonReader reader) {
    reader.next();

    var builder = SensorStateIon.builder();

    reader.stepIn();
    for (var type = reader.next(); type != null; type = reader.next()) {
      var name = reader.getFieldName();
      switch (name) {
        case FIELD_ID -> builder.id(reader.stringValue());
        case FIELD_READING -> builder.reading(deserializeReadingInternal(reader));
        default -> {} // Ignore unknown fields
      }
    }
    reader.stepOut();

    return builder.build();
  }

  @SuppressWarnings({"SwitchDefault", "PMD.SwitchStmtsShouldHaveDefault"})
  public static StateDurationIon deserializeStateDuration(IonReader reader) {
    reader.next();

    var builder = StateDurationIon.builder();

    reader.stepIn();
    for (var type = reader.next(); type != null; type = reader.next()) {
      var name = reader.getFieldName();
      switch (name) {
        case FIELD_ID -> builder.id(reader.stringValue());
        case FIELD_READING -> builder.reading(deserializeReadingInternal(reader));
        case FIELD_DURATION -> builder.duration(reader.longValue());
        default -> {} // Ignore unknown fields
      }
    }
    reader.stepOut();

    return builder.build();
  }
}
