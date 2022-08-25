package com.fillmore_labs.kafka.sensors.serde.json_iso.serialization;

import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.StateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json_iso.serialization.SerializationModule.JsonIso;
import dagger.Component;
import jakarta.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Singleton
@Component(modules = {SerializationModule.class})
public interface TestComponent {
  static TestComponent create() {
    return DaggerTestComponent.create();
  }

  @JsonIso
  Deserializer<SensorStateJson> deserializer();

  @JsonIso
  Serializer<StateDurationJson> serializerDuration();

  @JsonIso
  Deserializer<StateDurationJson> deserializerDuration();
}
