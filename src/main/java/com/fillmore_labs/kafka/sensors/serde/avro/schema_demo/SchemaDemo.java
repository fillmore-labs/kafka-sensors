package com.fillmore_labs.kafka.sensors.serde.avro.schema_demo;

import com.google.common.flogger.FluentLogger;
import com.google.common.io.BaseEncoding;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.SchemaValidationException;
import org.apache.avro.SchemaValidatorBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.MessageDecoder;
import org.apache.avro.message.RawMessageDecoder;
import org.apache.avro.message.RawMessageEncoder;

public final class SchemaDemo {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private final Schema schemaV1;
  private final Schema schemaV2;

  private final GenericData model;

  private final GenericRecord recordV1;
  private final GenericRecord recordV2;

  public SchemaDemo() {
    var clazz = this.getClass();
    this.schemaV1 = getSchema(clazz, "V1.avsc");
    this.schemaV2 = getSchema(clazz, "V2.avsc");

    this.model = GenericData.get();

    this.recordV1 =
        new GenericRecordBuilder(schemaV1).set("field1", "content1").set("field2", 2).build();
    this.recordV2 =
        new GenericRecordBuilder(schemaV2)
            .set("one", "contentOne")
            .set("three", "contentThree")
            .build();
  }

  /**
   * Reads a Avro schema from a relative resource path.
   *
   * @param clazz the class which package defines the start
   * @param name the resources name
   * @return an Avro schema
   */
  private static Schema getSchema(Class<?> clazz, String name) {
    try (var resourceStream = clazz.getResourceAsStream(name)) {
      if (resourceStream == null) {
        throw new FileNotFoundException(String.format("Resource %s not found", name));
      }
      return new Schema.Parser().parse(resourceStream);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  /**
   * Try to decode a serialized message with the provided decoder.
   *
   * @param original the original message, for logging
   * @param encoded the serialized message
   * @param decoder the Avro decoder
   */
  private static void tryDecode(
      GenericRecord original, ByteBuffer encoded, MessageDecoder<GenericRecord> decoder) {
    try {
      var decoded = decoder.decode(encoded);
      logger.atInfo().log("Decoded: %s, Original: %s", decoded, original);
    } catch (AvroRuntimeException | IOException e) {
      logger.atWarning().withCause(e).log("Decoding of %s failed:", original);
    }
  }

  public String hex(ByteBuffer buffer) {
    var bytes = new byte[buffer.limit()];
    buffer.get(0, bytes);
    var baseEncoding = BaseEncoding.base16().withSeparator(" ", 2);
    return baseEncoding.encode(bytes);
  }

  /** Validate that both schemate are read-compatible to each other. */
  public void validateSchemata() {
    try {
      var schemaValidator = new SchemaValidatorBuilder().mutualReadStrategy().validateAll();
      schemaValidator.validate(schemaV1, List.of(schemaV2));
      logger.atInfo().log("Schemata mutual compatible");
    } catch (SchemaValidationException e) {
      logger.atWarning().withCause(e).log("Schemata incompatible");
    }
  }

  /** Trys raw encoding (without header) with {@link RawMessageEncoder}. */
  public void tryRawEncoding() {
    ByteBuffer encodedV1;
    ByteBuffer encodedV2;

    try {
      var encoderV1 = new RawMessageEncoder<GenericRecord>(model, schemaV1);
      var encoderV2 = new RawMessageEncoder<GenericRecord>(model, schemaV2);

      encodedV1 = encoderV1.encode(recordV1);
      encodedV2 = encoderV2.encode(recordV2);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    logger.atInfo().log("Message 1, raw: %s", hex(encodedV1));
    logger.atInfo().log("Message 2, raw: %s", hex(encodedV2));

    var decoderV1 = new RawMessageDecoder<GenericRecord>(model, schemaV1);
    var decoderV2 = new RawMessageDecoder<GenericRecord>(model, schemaV2);

    logger.atInfo().log("--- Same schema, raw");
    tryDecode(recordV1, encodedV1, decoderV1);
    tryDecode(recordV2, encodedV2, decoderV2);

    logger.atInfo().log("--- Swapped schema, raw (fails)");
    tryDecode(recordV1, encodedV1, decoderV2);
    tryDecode(recordV2, encodedV2, decoderV1);

    var decoderV3 = new RawMessageDecoder<GenericRecord>(model, schemaV1, schemaV2);
    var decoderV4 = new RawMessageDecoder<GenericRecord>(model, schemaV2, schemaV1);

    logger.atInfo().log("--- Swapped schema, raw + reader schema");
    tryDecode(recordV1, encodedV1, decoderV3);
    tryDecode(recordV2, encodedV2, decoderV4);
  }

  /** Trys binary encoding (with header) with {@link BinaryMessageEncoder}. */
  public void tryBinaryEncoding() {
    ByteBuffer encodedV1;
    ByteBuffer encodedV2;

    try {
      var encoderV1 = new BinaryMessageEncoder<GenericRecord>(model, schemaV1);
      var encoderV2 = new BinaryMessageEncoder<GenericRecord>(model, schemaV2);

      encodedV1 = encoderV1.encode(recordV1);
      encodedV2 = encoderV2.encode(recordV2);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    logger.atInfo().log("Message 1, binary: %s", hex(encodedV1));
    logger.atInfo().log("Message 2, binary: %s", hex(encodedV2));

    var decoderV1 = new BinaryMessageDecoder<GenericRecord>(model, schemaV1);
    var decoderV2 = new BinaryMessageDecoder<GenericRecord>(model, schemaV2);

    logger.atInfo().log("--- Same schema, with header");
    tryDecode(recordV1, encodedV1, decoderV1);
    tryDecode(recordV2, encodedV2, decoderV2);

    logger.atInfo().log("--- Swapped schema, with header (fails)");
    tryDecode(recordV1, encodedV1, decoderV2);
    tryDecode(recordV2, encodedV2, decoderV1);

    decoderV1.addSchema(schemaV2);
    decoderV2.addSchema(schemaV1);

    logger.atInfo().log("--- Swapped schema, with header + added schema");
    tryDecode(recordV1, encodedV1, decoderV2);
    tryDecode(recordV2, encodedV2, decoderV1);
  }
}
