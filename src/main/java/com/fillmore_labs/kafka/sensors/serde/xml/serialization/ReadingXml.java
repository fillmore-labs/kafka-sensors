package com.fillmore_labs.kafka.sensors.serde.xml.serialization;

import com.google.common.base.MoreObjects;
import java.time.Instant;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness:initialization.field.uninitialized")
@XmlRootElement(name = "Reading")
@XmlType(name = "")
public final class ReadingXml {
  @XmlAttribute
  @XmlJavaTypeAdapter(InstantXmlAdapter.class)
  public Instant time;

  @XmlAttribute public Position position;

  @Override
  public int hashCode() {
    return Objects.hash(time, position);
  }

  @Override
  @SuppressWarnings("OperatorPrecedence")
  public boolean equals(@Nullable Object o) {
    return o == this
        || o instanceof ReadingXml that
            && Objects.equals(time, that.time)
            && position == that.position;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("time", time).add("position", position).toString();
  }

  @XmlType
  @XmlEnum(String.class)
  public enum Position {
    @XmlEnumValue("off")
    OFF,

    @XmlEnumValue("on")
    ON
  }
}
