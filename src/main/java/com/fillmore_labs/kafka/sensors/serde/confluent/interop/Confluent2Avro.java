package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.inject.Inject;
import org.apache.avro.Schema;
import org.apache.avro.SchemaNormalization;
import org.apache.avro.message.SchemaStore.Cache;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Demonstrates the possibility to convert between Apache Avro and Confluent Avro serializations.
 *
 * <p>Note that this implementation is just a proof of concept and inefficient.
 */
public final class Confluent2Avro {
  private static final byte[] AVRO_MAGIC = {(byte) 0xc3, (byte) 0x01};
  private static final byte CONFLUENT_MAGIC = (byte) 0;

  private final Cache resolver;
  private final SchemaRegistryClient registryClient;

  @Inject
  /* package */ Confluent2Avro(Cache resolver, SchemaRegistryClient registryClient) {
    this.resolver = resolver;
    this.registryClient = registryClient;
  }

  private static void checkMagic(byte magic) {
    if (magic != CONFLUENT_MAGIC) {
      throw new IllegalArgumentException("Confluent magic byte missing");
    }
  }

  private static byte[] packResult(long fingerprint, ByteBuffer buffer) {
    var result = ByteBuffer.allocate(buffer.remaining() + 10).order(ByteOrder.LITTLE_ENDIAN);
    result.put(AVRO_MAGIC);
    result.putLong(fingerprint);
    result.put(buffer);

    return result.array();
  }

  public byte @Nullable [] transform(String topic, byte @Nullable [] encoded) {
    if (encoded == null || encoded.length == 0) {
      return null;
    }
    var buffer = ByteBuffer.wrap(encoded).order(ByteOrder.BIG_ENDIAN);

    var magic = buffer.get();
    checkMagic(magic);

    var schemaID = buffer.getInt();
    var schema = lookupInConfluentSchemaRegistry(schemaID);
    var fingerprint = registerInInSchemaStore(schema);

    return packResult(fingerprint, buffer);
  }

  private Schema lookupInConfluentSchemaRegistry(int schemaID) {
    try {
      var parsedSchema = registryClient.getSchemaById(schemaID);
      var rawSchema = parsedSchema.rawSchema();
      if (rawSchema instanceof Schema) {
        return (Schema) rawSchema;
      }
    } catch (IOException | RestClientException e) {
      throw new IllegalStateException("Error reading from Confluent registry", e);
    }
    throw new IllegalArgumentException("No Avro Schema in Confluent registry");
  }

  private long registerInInSchemaStore(Schema rawSchema) {
    resolver.addSchema(rawSchema);
    return SchemaNormalization.parsingFingerprint64(rawSchema);
  }
}
