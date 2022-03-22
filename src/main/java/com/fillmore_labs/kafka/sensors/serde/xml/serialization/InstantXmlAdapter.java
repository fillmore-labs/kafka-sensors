package com.fillmore_labs.kafka.sensors.serde.xml.serialization;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.kafka.common.errors.SerializationException;

public final class InstantXmlAdapter extends XmlAdapter<String, Instant> {
  @Override
  public Instant unmarshal(String string) {
    try {
      return Instant.parse(string);
    } catch (DateTimeParseException e) {
      throw new SerializationException("Error while parsing date from XML", e);
    }
  }

  @Override
  public String marshal(Instant time) {
    return time.toString();
  }
}
