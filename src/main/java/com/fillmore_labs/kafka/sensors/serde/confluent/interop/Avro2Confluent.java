package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.serializers.subject.TopicNameStrategy;
import io.confluent.kafka.serializers.subject.strategy.SubjectNameStrategy;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import javax.inject.Inject;
import org.apache.avro.Schema;
import org.apache.avro.message.SchemaStore.Cache;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Demonstrates the possibility to convert between Apache Avro and Confluent Avro serializations.
 *
 * <p>Note that this implementation is just a proof of concept and inefficient.
 */
public final class Avro2Confluent {
  private static final byte[] AVRO_MAGIC = {(byte) 0xc3, (byte) 0x01};
  private static final byte CONFLUENT_MAGIC = (byte) 0;
  private static final int AVRO_HEADER_LEN = 10;

  private final Cache resolver;
  private final SchemaRegistryClient registryClient;

  private final SubjectNameStrategy subjectNameStrategy;

  @Inject
  /* package */ Avro2Confluent(Cache resolver, SchemaRegistryClient registryClient) {
    this.resolver = resolver;
    this.registryClient = registryClient;

    this.subjectNameStrategy = new TopicNameStrategy();
  }

  private static void checkMagic(byte[] magic) {
    if (!Arrays.equals(magic, AVRO_MAGIC)) {
      throw new IllegalArgumentException("Avro magic mismatch");
    }
  }

  private static byte[] packResult(int schemaID, ByteBuffer buffer) {
    var result = ByteBuffer.allocate(buffer.remaining() + 5).order(ByteOrder.BIG_ENDIAN);
    result.put(CONFLUENT_MAGIC);
    result.putInt(schemaID);
    result.put(buffer);

    return result.array();
  }

  public byte @Nullable [] transform(String topic, byte @Nullable [] encoded) {
    if (encoded == null || encoded.length == 0) {
      return null;
    }
    if (encoded.length < AVRO_HEADER_LEN) {
      throw new IllegalArgumentException("Invalid Avro single-object encoding");
    }
    var buffer = ByteBuffer.wrap(encoded).order(ByteOrder.LITTLE_ENDIAN);

    var magic = new byte[AVRO_MAGIC.length];
    buffer.get(magic);
    checkMagic(magic);

    var fingerprint = buffer.getLong();
    var schema = lookupInSchemaStore(fingerprint);
    var schemaID = registerInConfluentSchemaRegistry(topic, schema);

    return packResult(schemaID, buffer);
  }

  private int registerInConfluentSchemaRegistry(String topic, Schema schema) {
    try {
      var parsedSchema = new AvroSchema(schema);
      var subjectName = subjectNameStrategy.subjectName(topic, /* isKey= */ false, parsedSchema);
      return registryClient.register(subjectName, parsedSchema);
    } catch (IOException | RestClientException e) {
      throw new IllegalStateException("Error registring to Confluent registry", e);
    }
  }

  private Schema lookupInSchemaStore(long fingerprint) {
    var schema = resolver.findByFingerprint(fingerprint);
    if (schema == null) {
      throw new IllegalStateException("Avro Schema not found in schema store");
    }
    return schema;
  }
}
