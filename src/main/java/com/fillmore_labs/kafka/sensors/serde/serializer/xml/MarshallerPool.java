package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import com.sun.istack.Pool;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
