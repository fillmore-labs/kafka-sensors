package com.fillmore_labs.kafka.sensors.serde.xml.serialization;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@XmlRootElement(name = "SensorState")
@XmlAccessorType(XmlAccessType.FIELD)
public final class SensorStateXml {
  @XmlAttribute public String id;

  public ReadingXml reading;

  @Override
  public int hashCode() {
    return Objects.hash(id, reading);
  }

  @Override
  @SuppressWarnings("OperatorPrecedence")
  public boolean equals(@Nullable Object o) {
    return this == o
        || o instanceof SensorStateXml that
            && Objects.equals(id, that.id)
            && Objects.equals(reading, that.reading);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("reading", reading).toString();
  }
}
