package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public final class MarshallerPoolTest {
  @Mock
  @SuppressWarnings("nullness:initialization.field.uninitialized")
  public JAXBContext jaxbContext;

  @Test
  @SuppressWarnings("nullness:argument") // JAXBException is not annotated
  public void testTake() throws JAXBException {
    when(jaxbContext.createMarshaller()).thenThrow(new JAXBException((String) null));

    var marshallerPool = new MarshallerPool(jaxbContext);

    var exception = assertThrows(SerializationException.class, marshallerPool::take);
    assertThat(exception).hasCauseThat().isInstanceOf(JAXBException.class);
  }
}
