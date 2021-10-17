package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.fillmore_labs.kafka.sensors.serde.converter.DurationDecimalHelper;
import com.fillmore_labs.kafka.sensors.serde.converter.InstantDecimalHelper;
import java.io.IOException;

public final class IonSerializerHelper {
  private static final String FIELD_ID = "id";
  private static final String FIELD_STATE = "state";
  private static final String FIELD_TIME = "time";
  private static final String FIELD_EVENT = "event";
  private static final String FIELD_DURATION = "duration";

  private IonSerializerHelper() {}

  public static void serializeSensorStateDuration(IonWriter writer, SensorStateDurationIon message)
      throws IOException {
    writer.stepIn(IonType.STRUCT);
    writer.setFieldName(FIELD_EVENT);
    serializeSensorState(writer, message.getEvent());
    writer.setFieldName(FIELD_DURATION);
    writer.writeDecimal(DurationDecimalHelper.duration2Decimal(message.getDuration()));
    writer.stepOut();
  }

  public static void serializeSensorState(IonWriter writer, SensorStateIon message)
      throws IOException {
    writer.stepIn(IonType.STRUCT);
    writer.setFieldName(FIELD_ID);
    writer.writeString(message.getId());
    writer.setFieldName(FIELD_STATE);
    writer.writeSymbol(message.getState().name());
    writer.setFieldName(FIELD_TIME);
    writer.writeDecimal(InstantDecimalHelper.instant2Decimal(message.getTime()));
    writer.stepOut();
  }

  public static SensorStateDurationIon deserializeSensorStateDuration(IonReader reader) {
    reader.next();

    var builder = SensorStateDurationIon.builder();

    reader.stepIn();
    for (var type = reader.next(); type != null; type = reader.next()) {
      var name = reader.getFieldName();
      switch (name) {
        case FIELD_EVENT:
          builder.event(deserializeSensorState2(reader));
          break;

        case FIELD_DURATION:
          builder.duration(DurationDecimalHelper.decimal2Duration(reader.bigDecimalValue()));
          break;

        default:
          // Ignore unknown fields
          break;
      }
    }
    reader.stepOut();

    return builder.build();
  }

  public static SensorStateIon deserializeSensorState(IonReader reader) {
    reader.next();
    return deserializeSensorState2(reader);
  }

  private static SensorStateIon deserializeSensorState2(IonReader reader) {
    var builder = SensorStateIon.builder();

    reader.stepIn();
    for (var type = reader.next(); type != null; type = reader.next()) {
      var name = reader.getFieldName();
      switch (name) {
        case FIELD_ID:
          builder.id(reader.stringValue());
          break;

        case FIELD_STATE:
          builder.state(SensorStateIon.State.valueOf(reader.stringValue()));
          break;

        case FIELD_TIME:
          builder.time(InstantDecimalHelper.decimal2Instant(reader.bigDecimalValue()));
          break;

        default:
          // Ignore unknown fields
          break;
      }
    }
    reader.stepOut();

    return builder.build();
  }
}
