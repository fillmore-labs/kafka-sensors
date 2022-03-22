package com.fillmore_labs.kafka.sensors.serde.xml.serialization;

import com.google.common.base.MoreObjects;
import java.time.Duration;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@XmlRootElement(name = "StateDuration")
@XmlAccessorType(XmlAccessType.FIELD)
public final class StateDurationXml {
  @XmlAttribute public String id;

  public ReadingXml reading;

  @XmlAttribute
  @XmlJavaTypeAdapter(DurationXmlAdapter.class)
  public Duration duration;

  @Override
  public int hashCode() {
    return Objects.hash(id, reading, duration);
  }

  @Override
  @SuppressWarnings("OperatorPrecedence")
  public boolean equals(@Nullable Object o) {
    return this == o
        || o instanceof StateDurationXml that
            && Objects.equals(id, that.id)
            && Objects.equals(reading, that.reading)
            && Objects.equals(duration, that.duration);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("reading", reading)
        .add("duration", duration)
        .toString();
  }
}
