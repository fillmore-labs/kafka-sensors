package com.fillmore_labs.kafka.sensors.serde.confluent.avro_demo;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

public final class DecodingSampleTest {
  private final DecodingSample decodingSample = new DecodingSample();

  @Test
  public void tryGeneric() {
    decodingSample.tryGeneric();
  }

  @Test
  public void validateAvroCompatibility() {
    decodingSample.validateAvroCompatibility();
  }

  @Test
  public void trySpecific() {
    decodingSample.trySpecific();
  }

  @Test
  public void validateConfluentCompatibility() {
    decodingSample.validateConfluentCompatibility();
  }

  @Test
  public void trySpecificConfluent() {
    assertThrows(ClassCastException.class, decodingSample::trySpecificConfluent);
  }
}
