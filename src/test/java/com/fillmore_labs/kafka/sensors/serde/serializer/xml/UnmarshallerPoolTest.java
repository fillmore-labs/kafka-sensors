package com.fillmore_labs.kafka.sensors.serde.serializer.xml;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public final class UnmarshallerPoolTest {
  @Mock
  @SuppressWarnings("nullness:initialization.field.uninitialized")
  public JAXBContext jaxbContext;

  @Test
  public void testTake() throws JAXBException {
    given(jaxbContext.createUnmarshaller()).willThrow(JAXBException.class);

    var unmarshallerPool = new UnmarshallerPool(jaxbContext);

    var exception = assertThrows(SerializationException.class, unmarshallerPool::take);
    assertThat(exception).hasCauseThat().isInstanceOf(JAXBException.class);
  }
}
