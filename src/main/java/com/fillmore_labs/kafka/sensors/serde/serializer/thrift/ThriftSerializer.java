package com.fillmore_labs.kafka.sensors.serde.serializer.thrift;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.thrift.TBase;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.AutoExpandingBufferWriteTransport;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class ThriftSerializer<T extends TBase<T, ?>> implements Serializer<T> {
  @Override
  @SuppressWarnings("nullness:override.return") // Serializer is not annotated
  public byte @Nullable [] serialize(String topic, @Nullable T thrift) {
    if (thrift == null) {
      return null;
    }
    try (var otrans = new AutoExpandingBufferWriteTransport(new TConfiguration(), 100, 0)) {
      var oprot = new TBinaryProtocol(otrans);
      thrift.write(oprot);

      var buf = otrans.getBuf();
      return buf.array();
    } catch (TException e) {
      var message = String.format("Error while writing Thrift to topic \"%s\"", topic);
      throw new SerializationException(message, e);
    }
  }
}
