package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import com.sun.istack.Pool;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
      throw new DataBindingException(e);
    }
  }
}
