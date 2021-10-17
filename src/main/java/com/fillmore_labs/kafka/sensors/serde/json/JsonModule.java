package com.fillmore_labs.kafka.sensors.serde.json;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.json.mapper.JsonMapperModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, JsonMapperModule.class})
public abstract class JsonModule {
  private JsonModule() {}

  @Provides
  @Format(Name.JSON)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<SensorStateJson> serializer,
      Deserializer<SensorStateJson> deserializer,
      BiMapper<SensorState, SensorStateJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.JSON)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      Serializer<SensorStateDurationJson> serializer,
      Deserializer<SensorStateDurationJson> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
