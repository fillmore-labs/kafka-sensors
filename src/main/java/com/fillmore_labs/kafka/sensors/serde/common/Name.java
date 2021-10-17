package com.fillmore_labs.kafka.sensors.serde.common;

public enum Name {
  AVRO_DIRECT(Format.AVRO),
  AVRO_GENERIC(Format.AVRO),
  AVRO_REFLECT(Format.AVRO),
  AVRO_SPECIFIC(Format.AVRO),
  CONFLUENT_DIRECT(Format.CONFLUENT_AVRO),
  CONFLUENT_GENERIC(Format.CONFLUENT_AVRO),
  CONFLUENT_REFLECT(Format.CONFLUENT_AVRO),
  CONFLUENT_SPECIFIC(Format.CONFLUENT_AVRO),
  CONFLUENT_JSON(Format.CONFLUENT_JSON),
  CONFLUENT_PROTO(Format.CONFLUENT_PROTO),
  ION_BINARY(Format.ION),
  ION_TEXT(Format.ION),
  JSON(Format.JSON),
  MIXIN(Format.JSON),
  PROTO(Format.PROTO),
  GSON(Format.JSON),
  GSON_FAST(Format.JSON);

  private final Format format;

  Name(Format format) {
    this.format = format;
  }

  public Format format() {
    return format;
  }

  public enum Format {
    JSON,
    PROTO,
    AVRO,
    CONFLUENT_AVRO,
    CONFLUENT_JSON,
    CONFLUENT_PROTO,
    ION,
  }
}
