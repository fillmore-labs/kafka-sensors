package com.fillmore_labs.kafka.sensors.serde.xml.serialization;

import static org.junit.Assert.assertThrows;

import org.apache.kafka.common.errors.SerializationException;
import org.junit.Test;

public final class InstantXmlAdapterTest {
  @Test
  public void unmarshalInvalid() {
    var adapter = new InstantXmlAdapter();
    assertThrows(SerializationException.class, () -> adapter.unmarshal("INVALID"));
  }
}
