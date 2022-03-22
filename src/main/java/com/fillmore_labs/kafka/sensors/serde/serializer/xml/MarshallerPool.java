package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import com.sun.istack.Pool;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
      throw new DataBindingException(e);
    }
  }
}
