package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import com.sun.istack.Pool;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.kafka.common.errors.SerializationException;

@Singleton
public final class UnmarshallerPool extends Pool.Impl<Unmarshaller> {
  private final JAXBContext jaxbContext;

  @Inject
  /* package */ UnmarshallerPool(JAXBContext jaxbContext) {
    super();
    this.jaxbContext = jaxbContext;
  }

  @Override
  protected Unmarshaller create() {
    try {
      return jaxbContext.createUnmarshaller();
    } catch (JAXBException e) {
      throw new SerializationException("Can't create unmarshaller", e);
    }
  }
}
