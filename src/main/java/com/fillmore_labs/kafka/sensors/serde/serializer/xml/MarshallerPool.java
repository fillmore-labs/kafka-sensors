package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import com.sun.istack.Pool;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.kafka.common.errors.SerializationException;

@Singleton
public final class MarshallerPool extends Pool.Impl<Marshaller> {
  private final JAXBContext jaxbContext;

  @Inject
  /* package */ MarshallerPool(JAXBContext jaxbContext) {
    super();
    this.jaxbContext = jaxbContext;
  }

  @Override
  protected Marshaller create() {
    try {
      return jaxbContext.createMarshaller();
    } catch (JAXBException e) {
      throw new SerializationException("Can't create marshaller", e);
    }
  }
}
