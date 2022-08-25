package com.fillmore_labs.kafka.sensors.serde.all_serdes.context;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.all_serdes.AllSerdesModule;
import com.fillmore_labs.kafka.sensors.serde.avro.schema_store.SchemaStoreModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import dagger.Component;
import dagger.Module;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.Map;
import org.apache.kafka.common.serialization.Serde;

@Singleton
@Component(modules = {TestComponent.TestModule.class})
public interface TestComponent {
  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  @Named("encoding")
  Map<String, String> encodings();

  Map<String, Serde<Reading>> serdeMapReading();

  Map<String, Serde<SensorState>> serdeMap();

  Map<String, Serde<StateDuration>> serdeDurationMap();

  Parameters parameters();

  @Module(
      includes = {AllSerdesModule.class, SchemaStoreModule.class, SchemaRegistryModule.class},
      subcomponents = SingleTestComponent.class)
  /* package */ abstract class TestModule {
    private TestModule() {}
  }
}
