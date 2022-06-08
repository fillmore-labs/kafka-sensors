package com.fillmore_labs.kafka.sensors.serde.serializer.thrift;

import java.util.function.Supplier;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class ThriftDeserializer<T extends TBase<T, F>, F extends TFieldIdEnum>
    implements Deserializer<T> {
  private final Supplier<T> tSupplier;

  public ThriftDeserializer(Supplier<T> tSupplier) {
    this.tSupplier = tSupplier;
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Deserializer is not annotated
  public @Nullable T deserialize(String topic, byte @Nullable [] data) {
    if (data == null || data.length == 0) {
      return null;
    }
    try (var itrans = new TMemoryInputTransport(data)) {
      var iprot = new TBinaryProtocol(itrans);
      var t = tSupplier.get();
      t.read(iprot);
      return t;
    } catch (TException e) {
      var message = String.format("Error while reading Thrift from topic \"%s\"", topic);
      throw new SerializationException(message, e);
    }
  }
}
