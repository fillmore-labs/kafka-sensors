package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import com.sun.istack.Pool;
import java.io.ByteArrayInputStream;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class XmlDeserializer<T> implements Deserializer<T> {
  private final Class<T> type;
  private final Pool<Unmarshaller> unmarshallerPool;

  public XmlDeserializer(Pool<Unmarshaller> unmarshallerPool, Class<T> type) {
    this.unmarshallerPool = unmarshallerPool;
    this.type = type;
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Deserializer is not annotated
  public @Nullable T deserialize(String topic, byte @Nullable [] data) {
    if (data == null || data.length == 0) {
      return null;
    }

    var stream = new ByteArrayInputStream(data);

    var unmarshaller = unmarshallerPool.take();
    try {
      return type.cast(unmarshaller.unmarshal(stream));
    } catch (JAXBException | ClassCastException e) {
      throw new SerializationException("Can't unmarshal XML", e);
    } finally {
      unmarshallerPool.recycle(unmarshaller);
    }
  }
}
