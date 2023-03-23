package com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.google.testing.junit.testparameterinjector.TestParameterInjector;
import com.google.testing.junit.testparameterinjector.TestParameters;
import com.google.testing.junit.testparameterinjector.TestParameters.TestParametersValues;
import com.google.testing.junit.testparameterinjector.TestParameters.TestParametersValuesProvider;
import java.util.List;
import org.apache.avro.generic.GenericEnumSymbol;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(TestParameterInjector.class)
public final class PositionMapperTest {
  private final PositionMapper positionMapper = new PositionMapper();

  @Test
  @TestParameters(valuesProvider = MapParameters.class)
  public void testMap(Reading.Position position, String data) {
    var result = positionMapper.map(position);
    assertThat(result).isInstanceOf(GenericEnumSymbol.class);
    assertThat(result.toString()).isEqualTo(data);
  }

  @Test
  @TestParameters(valuesProvider = MapParameters.class)
  public void testUnmap(String data, Reading.Position position) {
    assertThat(positionMapper.unmap(data)).isEqualTo(position);
  }

  @Test
  public void testInvalidUnmap() {
    assertThrows(IllegalArgumentException.class, () -> positionMapper.unmap("XXX"));
  }

  public static final class MapParameters implements TestParametersValuesProvider {
    @Override
    public List<TestParametersValues> provideValues() {
      return List.of(
          TestParametersValues.builder()
              .name("OFF mapping")
              .addParameter("data", "OFF")
              .addParameter("position", Reading.Position.OFF)
              .build(),
          TestParametersValues.builder()
              .name("OFF mapping")
              .addParameter("data", "ON")
              .addParameter("position", Reading.Position.ON)
              .build());
    }
  }
}
