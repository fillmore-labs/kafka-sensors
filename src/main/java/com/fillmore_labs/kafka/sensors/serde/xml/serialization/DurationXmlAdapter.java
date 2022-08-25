package com.fillmore_labs.kafka.sensors.serde.xml.serialization;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import org.apache.kafka.common.errors.SerializationException;

public final class DurationXmlAdapter extends XmlAdapter<String, Duration> {

  @Override
  public Duration unmarshal(String string) {
    try {
      return Duration.parse(string);
    } catch (DateTimeParseException e) {
      throw new SerializationException("Error while parsing duration from XML", e);
    }
  }

  @Override
  public String marshal(Duration duration) {
    return duration.toString();
  }
}
