package com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.google.testing.junit.testparameterinjector.TestParameterInjector;
import com.google.testing.junit.testparameterinjector.TestParameters;
import org.apache.avro.generic.GenericEnumSymbol;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(TestParameterInjector.class)
public class PositionMapperTest {
  private final PositionMapper positionMapper = new PositionMapper();

  @Test
  @TestParameters("{position: 'OFF', result: 'OFF'}")
  @TestParameters("{position: 'ON', result: 'ON'}")
  public void testMap(Reading.Position position, String result) {
    var data = positionMapper.map(position);
    assertThat(data).isInstanceOf(GenericEnumSymbol.class);
    assertThat(data.toString()).isEqualTo(result);
  }

  @Test
  @TestParameters("{data: 'OFF', position: 'OFF'}")
  @TestParameters("{data: 'ON', position: 'ON'}")
  public void testUnmap(String data, Reading.Position position) {
    assertThat(positionMapper.unmap(data)).isEqualTo(position);
  }

  @Test
  public void testInvalidUnmap() {
    assertThrows(IllegalArgumentException.class, () -> positionMapper.unmap("XXX"));
  }
}
