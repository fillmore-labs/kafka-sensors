package com.fillmore_labs.kafka.sensors.serde.confluent.avro_demo;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.fillmore_labs.kafka.sensors.avro.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.ReadingReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.StateDurationReflect;
import com.google.common.flogger.FluentLogger;
import io.confluent.kafka.schemaregistry.CompatibilityLevel;
import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.streams.serdes.avro.GenericAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.ReflectionAvroSerializer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.avro.Schema;
import org.apache.avro.SchemaValidationException;
import org.apache.avro.SchemaValidatorBuilder;
import org.apache.kafka.common.serialization.Deserializer;

public final class Main {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private static final String REGISTRY_SCOPE = "demo09";
  private static final String REGISTRY_URL = "mock://" + REGISTRY_SCOPE;
  private static final String TOPIC = "topic";

  private Main() {}

  public static void main(String... args) {
    // Create a message with the reflection serializer
    var stateDurationReflect = createStateDuration();
    logger.atInfo().log("Serializing: %s", stateDurationReflect);
    var serialized = serialize(stateDurationReflect);

    // We use the generic deserializer to retrieve the schema of the written message
    var genericRecord = deserialize(serialized, GenericAvroDeserializer::new);
    logger.atInfo().log("Deserialized (generic): %s", genericRecord);

    var schema = genericRecord.getSchema();

    // We plan to read the message with the specific deserializer, so this is the reader schema
    var readerSchema = StateDuration.getClassSchema();

    // Check Avro compatibility via {@link org.apache.avro.SchemaValidator}
    try {
      validateSchema(schema, readerSchema);
      logger.atInfo().log("Schema is read compatible");
    } catch (SchemaValidationException e) {
      logger.atWarning().withCause(e).log("Schema mismatch");
    }

    // Now read with our deserializer
    var stateDurationSpecific =
        deserialize(
            serialized,
            () ->
                new com.fillmore_labs.kafka.sensors.serde.serializer.confluent
                    .SpecificAvroDeserializer<>(StateDuration.class));
    logger.atInfo().log("Deserialized (specific): %s", stateDurationSpecific);

    // Retrieve a “parsed schema” from the Confluent registry
    var registryClient = MockSchemaRegistry.getClientForScope(REGISTRY_SCOPE);
    var parsedSchema = retrieveSchema(registryClient, serialized);

    // Now check for Confluent compatibility
    var parsedReaderSchema = new AvroSchema(readerSchema);
    var violations =
        parsedSchema.isCompatible(CompatibilityLevel.FORWARD, List.of(parsedReaderSchema));
    for (var problem : violations) {
      logger.atWarning().log("Confluent compatability problem: %s", problem);
    }

    // ... try to deserialize nevertheless, which ends in a ClassCastException
    var deserializdeConfluentSpecific =
        deserialize(
            serialized,
            io.confluent.kafka.streams.serdes.avro.SpecificAvroDeserializer<StateDuration>::new);
    // unreachable
    logger.atInfo().log("Deserialized (Confluent Specific): %s", deserializdeConfluentSpecific);
  }

  /**
   * Supplies a sample {@link StateDurationReflect}.
   *
   * @return a sample value
   */
  private static StateDurationReflect createStateDuration() {
    var reading = new ReadingReflect();
    reading.time = Instant.parse("2020-01-01T12:34:56.789012345Z");
    reading.position = ReadingReflect.Position.OFF;

    var stateDuration = new StateDurationReflect();
    stateDuration.id = "sample";
    stateDuration.reading = reading;
    stateDuration.duration = Duration.ofNanos(12_345_678_901L);

    return stateDuration;
  }

  /**
   * Serializes a value using the reflective Confluent Avro serializer.
   *
   * @param sensorState the value
   * @param <T> the type, discovered by reflection
   * @return serialized bytes in Confluent Avro format
   */
  private static <T> byte[] serialize(T sensorState) {
    try (var serializer = new ReflectionAvroSerializer<T>()) {
      var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, REGISTRY_URL);
      serializer.configure(config, /* isSerializerForRecordKeys= */ false);

      return serializer.serialize(TOPIC, sensorState);
    }
  }

  /**
   * Deserializes Confluent Avro format using a provided deserializer.
   *
   * @param serialized serialized data
   * @param deserializerSupplier Confluent Avro deserializer supplier
   * @param <T> some type, depending on the deserializer
   * @return the deserialized value
   */
  private static <T> T deserialize(
      byte[] serialized, Supplier<Deserializer<T>> deserializerSupplier) {
    try (var deserializer = deserializerSupplier.get()) {
      var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, REGISTRY_URL);
      deserializer.configure(config, /* isKey= */ false);

      var deserialized = deserializer.deserialize(TOPIC, serialized);
      if (deserialized == null) {
        throw new NullPointerException();
      }
      return deserialized;
    }
  }

  /**
   * Validates whether the reader schema can read data written with the writer schema, using Apache
   * Avro functions.
   *
   * @param schema the writer schema
   * @param readerSchema the reader schema
   * @throws SchemaValidationException when schemas mismatch
   */
  private static void validateSchema(Schema schema, Schema readerSchema)
      throws SchemaValidationException {
    var validator = new SchemaValidatorBuilder().canBeReadStrategy().validateAll();

    validator.validate(schema, List.of(readerSchema));
  }

  /**
   * Retrieve the schema of the serialized message from the registry.
   *
   * @param registryClient client to the Confluent schema registry
   * @param serialized serialized message
   * @return schema of the message
   */
  private static ParsedSchema retrieveSchema(
      SchemaRegistryClient registryClient, byte[] serialized) {
    try {
      var buffer = ByteBuffer.wrap(serialized);
      if (buffer.get() != (byte) 0) {
        throw new IllegalStateException("No Confluent magic byte");
      }
      var schemaId = buffer.getInt();
      return registryClient.getSchemaById(schemaId);
    } catch (IOException | RestClientException e) {
      throw new IllegalStateException("Can't retrieve schema from registry", e);
    }
  }
}
