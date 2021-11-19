package com.fillmore_labs.kafka.sensors.serde.confluent.avro_demo;

import com.fillmore_labs.kafka.sensors.avro.StateDuration;
import com.google.common.flogger.FluentLogger;
import io.confluent.kafka.schemaregistry.CompatibilityLevel;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.streams.serdes.avro.GenericAvroDeserializer;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.avro.SchemaValidationException;
import org.apache.avro.SchemaValidatorBuilder;
import org.apache.avro.generic.GenericRecord;

public final class DecodingSample {
  /* package */ static final String TOPIC = "topic";
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private final byte[] serialized;

  public DecodingSample() {
    this.serialized = DecodingSampleHelper.createSerializedMessage();
  }

  /**
   * Deserializes Confluent Avro format using the Confluent generic deserializer.
   *
   * @return the deserialized value
   */
  private GenericRecord deserializeGeneric() {
    // The Confluent generic deserializer
    var deserializer = DecodingSampleHelper.configureDeserializer(new GenericAvroDeserializer());
    return deserializer.deserialize(TOPIC, serialized);
  }

  /**
   * Deserializes Confluent Avro format using our specific deserializer.
   *
   * @return the deserialized value
   */
  private StateDuration deserializeSpecific() {
    // Our specific deserializer for the Confluent format
    var deserializer =
        DecodingSampleHelper.configureDeserializer(
            new com.fillmore_labs.kafka.sensors.serde.serializer.confluent
                .SpecificAvroDeserializer<>(StateDuration.class));
    return deserializer.deserialize(TOPIC, serialized);
  }

  /**
   * Deserializes Confluent Avro format using the Confluent specific deserializer.
   *
   * @return the deserialized value
   */
  private StateDuration deserializeConfluentSpecific() {
    // The original Confluent specific deserializer
    var deserializer =
        DecodingSampleHelper.configureDeserializer(
            new io.confluent.kafka.streams.serdes.avro.SpecificAvroDeserializer<StateDuration>());
    return deserializer.deserialize(TOPIC, serialized);
  }

  public void tryGeneric() {
    var genericRecord = deserializeGeneric();
    logger.atInfo().log("Deserialized (generic): %s", genericRecord);
  }

  public void validateAvroCompatibility() {
    // Use the generic deserializer to retrieve the schema of a serialized message
    var genericRecord = deserializeGeneric();
    var writerSchema = genericRecord.getSchema();

    // We plan to read the message with the specific deserializer, so this is our reader schema
    var readerSchema = StateDuration.getClassSchema();

    try {
      // Check Avro compatibility via {@link org.apache.avro.SchemaValidator}
      var validator = new SchemaValidatorBuilder().canBeReadStrategy().validateAll();
      validator.validate(writerSchema, List.of(readerSchema));
      logger.atInfo().log("Schema is read compatible");
    } catch (SchemaValidationException e) {
      logger.atWarning().withCause(e).log("Schema mismatch");
    }
  }

  public void trySpecific() {
    var stateDurationSpecific = deserializeSpecific();
    logger.atInfo().log("Deserialized (specific): %s", stateDurationSpecific);
  }

  private int getSchemaId() {
    var buffer = ByteBuffer.wrap(serialized);
    if (buffer.get() != (byte) 0) {
      throw new IllegalStateException("No Confluent magic byte");
    }
    return buffer.getInt();
  }

  public void validateConfluentCompatibility() {
    // Retrieve a “parsed schema” from the Confluent registry
    var schemaID = getSchemaId();
    var parsedSchema = DecodingSampleHelper.retrieveSchema(schemaID);

    var parsedReaderSchema = new AvroSchema(StateDuration.getClassSchema());
    // Now check for Confluent compatibility
    var violations =
        parsedSchema.isCompatible(CompatibilityLevel.FORWARD, List.of(parsedReaderSchema));
    for (var problem : violations) {
      logger.atWarning().log("Confluent compatability problem: %s", problem);
    }
  }

  public void trySpecificConfluent() {
    var stateDurationSpecific = deserializeConfluentSpecific();
    logger.atInfo().log("Deserialized (Confluent specific): %s", stateDurationSpecific);
  }
}
