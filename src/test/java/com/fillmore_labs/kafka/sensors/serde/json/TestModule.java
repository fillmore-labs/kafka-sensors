package com.fillmore_labs.kafka.sensors.serde.json;

import static com.fillmore_labs.kafka.sensors.serde.json.JsonModule.JSON;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import dagger.Binds;
import dagger.Module;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Serde;

@Module(includes = JsonModule.class)
abstract class TestModule {
  private TestModule() {}

  @Binds
  abstract Serde<SensorState> sensorState(@Named(JSON) Serde<SensorState> serde);

  @Binds
  abstract Serde<SensorStateDuration> sensorStateDuration(
      @Named(JSON) Serde<SensorStateDuration> serde);
}
