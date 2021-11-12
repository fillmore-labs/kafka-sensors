package com.fillmore_labs.kafka.sensors.serde.confluent.avro_demo;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class MainTest {
  @Test
  public void testMain() {
    assertThrows(ClassCastException.class, Main::main);
  }
}
