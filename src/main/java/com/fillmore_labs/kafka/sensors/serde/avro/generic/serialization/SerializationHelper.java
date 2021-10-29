package com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.MessageDecoder;
import org.apache.avro.message.MessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class SerializationHelper {

  public static final MessageEncoder<GenericRecord> SENSOR_STATE_ENCODER;
  public static final MessageEncoder<GenericRecord> SENSOR_STATE_DURATION_ENCODER;

  static {
    SENSOR_STATE_ENCODER =
        new BinaryMessageEncoder<>(SensorStateSchema.MODEL, SensorStateSchema.SCHEMA);
    SENSOR_STATE_DURATION_ENCODER =
        new BinaryMessageEncoder<>(StateDurationSchema.MODEL, StateDurationSchema.SCHEMA);
  }

  private SerializationHelper() {}

  @SuppressWarnings("nullness:argument")
  public static MessageDecoder<GenericRecord> createSensorStateDecoder(
      @Nullable SchemaStore resolver) {
    return new BinaryMessageDecoder<>(SensorStateSchema.MODEL, SensorStateSchema.SCHEMA, resolver);
  }

  @SuppressWarnings("nullness:argument")
  public static MessageDecoder<GenericRecord> createStateDurationDecoder(
      @Nullable SchemaStore resolver) {
    return new BinaryMessageDecoder<>(
        StateDurationSchema.MODEL, StateDurationSchema.SCHEMA, resolver);
  }
}
