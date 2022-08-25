package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import com.sun.istack.Pool;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class XmlSerializer<T> implements Serializer<T> {
  private final Pool<Marshaller> marshallerPool;

  public XmlSerializer(Pool<Marshaller> marshallerPool) {
    this.marshallerPool = marshallerPool;
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Serializer is not annotated
  public byte @Nullable [] serialize(String topic, @Nullable T message) {
    if (message == null) {
      return null;
    }

    var stream = new ByteArrayOutputStream();

    var marshaller = marshallerPool.take();
    try {
      marshaller.marshal(message, stream);
    } catch (JAXBException e) {
      throw new SerializationException("Can't marshal XML", e);
    } finally {
      marshallerPool.recycle(marshaller);
    }

    return stream.toByteArray();
  }
}
