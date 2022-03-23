package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
  public void testTake() throws JAXBException {
    given(jaxbContext.createMarshaller()).willThrow(JAXBException.class);

    var marshallerPool = new MarshallerPool(jaxbContext);

    var exception = assertThrows(SerializationException.class, marshallerPool::take);
    assertThat(exception).hasCauseThat().isInstanceOf(JAXBException.class);
  }
}
