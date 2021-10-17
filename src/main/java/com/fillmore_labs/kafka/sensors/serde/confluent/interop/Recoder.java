package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.avro.AvroSerializer;
import io.confluent.kafka.streams.serdes.avro.GenericAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerializer;
import java.util.Map;
import javax.inject.Inject;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.message.SchemaStore.Cache;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Demonstrates the possibility to convert between Apache Avro and Confluent Avro serializations.
 *
 * <p>Note that this implementation is just a proof of concept and inefficient.
 */
public final class Recoder {
  private final Cache resolver;
  private final Deserializer<GenericRecord> avroDeserializer;

  private final Deserializer<GenericRecord> confluentDeserializer;
  private final Serializer<GenericRecord> confluentSerializer;

  @Inject
  /* package */ Recoder(Cache resolver, @SchemaRegistryUrl String registryUrl) {
    this.resolver = resolver;
    this.avroDeserializer = new AvroDeserializer<>(GenericData.get(), null, resolver);

    var config = Map.of(SCHEMA_REGISTRY_URL_CONFIG, registryUrl);

    this.confluentDeserializer = new GenericAvroDeserializer();
    this.confluentDeserializer.configure(config, /* isKey= */ false);

    this.confluentSerializer = new GenericAvroSerializer();
    this.confluentSerializer.configure(config, /* isKey= */ false);
  }

  public byte @Nullable [] confluent2Avro(byte @Nullable [] encoded) {
    if (encoded == null || encoded.length == 0) {
      return null;
    }
    var record = confluentDeserializer.deserialize("", encoded);

    var schema = record.getSchema();
    resolver.addSchema(schema);

    var avroSerializer = new AvroSerializer<GenericRecord>(GenericData.get(), schema);
    return avroSerializer.serialize("", record);
  }

  public byte @Nullable [] avro2Confluent(byte @Nullable [] encoded) {
    if (encoded == null || encoded.length == 0) {
      return null;
    }
    var record = avroDeserializer.deserialize("", encoded);
    return confluentSerializer.serialize("", record);
  }
}
