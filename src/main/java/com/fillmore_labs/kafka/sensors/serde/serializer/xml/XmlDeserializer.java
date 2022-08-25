package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import com.sun.istack.Pool;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class XmlDeserializer<T> implements Deserializer<T> {
  private final Class<T> type;
  private final Pool<Unmarshaller> unmarshallerPool;
  private final XMLInputFactory inputFactory;

  public XmlDeserializer(Pool<Unmarshaller> unmarshallerPool, Class<T> type) {
    this.unmarshallerPool = unmarshallerPool;
    this.type = type;
    this.inputFactory = XMLInputFactory.newFactory();
  }

  @Override
  @SuppressWarnings("nullness:override.return") // Deserializer is not annotated
  public @Nullable T deserialize(String topic, byte @Nullable [] data) {
    if (data == null || data.length == 0) {
      return null;
    }

    T result;

    var stream = new ByteArrayInputStream(data);
    var unmarshaller = unmarshallerPool.take();
    try {
      var streamReader = inputFactory.createXMLStreamReader(stream);
      var o = unmarshaller.unmarshal(streamReader);
      streamReader.close();
      result = type.cast(o);
    } catch (XMLStreamException | JAXBException | ClassCastException e) {
      throw new SerializationException("Can't unmarshal XML", e);
    } finally {
      unmarshallerPool.recycle(unmarshaller);
    }

    return result;
  }
}
