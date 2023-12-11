package com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public final class EqualsTest {
  private static final Class<?>[] CLASSES = {
    ReadingReflect.class, SensorStateReflect.class, StateDurationReflect.class
  };

  @Test
  public void equals() {
    EqualsVerifier.forClasses(List.of(CLASSES)).suppress(Warning.NONFINAL_FIELDS).verify();
  }

  @Test
  public void string() throws ReflectiveOperationException {
    for (var clazz : CLASSES) {
      var t = clazz.getDeclaredConstructor().newInstance();
      assertThat(t.toString()).isNotEmpty();
    }
  }
}
