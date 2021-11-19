package com.fillmore_labs.kafka.sensors.serde.confluent.avro_demo;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.ReadingReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.StateDurationReflect;
import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.streams.serdes.avro.ReflectionAvroSerializer;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public final class DecodingSampleHelper {
  private static final String REGISTRY_SCOPE = "demo09";
  private static final String REGISTRY_URL = "mock://" + REGISTRY_SCOPE;

  private DecodingSampleHelper() {}

  /**
   * Create a serialized message using the reflection serializer.
   *
   * @return serialized Message
   */
  static byte[] createSerializedMessage() {
    var stateDurationReflect = createStateDuration();
    var serializer = configureSerializer(new ReflectionAvroSerializer<StateDurationReflect>());
    return serializer.serialize(DecodingSample.TOPIC, stateDurationReflect);
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
   * Configures a Confluent serializer with the registry URL.
   *
   * @param serializer a serializer
   * @param <T> the serializers type
   * @return the serializer
   */
  private static <T> Serializer<T> configureSerializer(Serializer<T> serializer) {
    serializer.configure(Map.of(SCHEMA_REGISTRY_URL_CONFIG, REGISTRY_URL), /* isKey= */ false);
    return serializer;
  }

  /**
   * Configures a Confluent deserializer with the registry URL.
   *
   * @param deserializer a deserializer
   * @param <T> the deserializers type
   * @return the deserializer
   */
  static <T> Deserializer<T> configureDeserializer(Deserializer<T> deserializer) {
    deserializer.configure(Map.of(SCHEMA_REGISTRY_URL_CONFIG, REGISTRY_URL), /* isKey= */ false);
    return deserializer;
  }

  /**
   * Retrieve the schema of the serialized message from the registry.
   *
   * @param schemaId the Schema id of the message, assigned by the schema registry
   * @return schema of the message
   */
  static ParsedSchema retrieveSchema(int schemaId) {
    var registryClient = MockSchemaRegistry.getClientForScope(DecodingSampleHelper.REGISTRY_SCOPE);
    try {
      return registryClient.getSchemaById(schemaId);
    } catch (IOException | RestClientException e) {
      throw new IllegalStateException("Can't retrieve schema from registry", e);
    }
  }
}
