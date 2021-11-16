package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import static com.google.common.truth.Truth.assertThat;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class EqualsTest {
  @Test
  public void testEquals() {
    EqualsVerifier.forClasses(
            ReadingReflect.class, SensorStateReflect.class, StateDurationReflect.class)
        .suppress(Warning.NONFINAL_FIELDS)
        .verify();
  }

  @Test
  public void testToString1() {
    assertThat(new ReadingReflect().toString()).isNotEmpty();
  }

  @Test
  public void testToString2() {
    assertThat(new SensorStateReflect().toString()).isNotEmpty();
  }

  @Test
  public void testToString3() {
    assertThat(new StateDurationReflect().toString()).isNotEmpty();
  }
}
