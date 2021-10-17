package com.fillmore_labs.kafka.sensors.serde.confluent.json;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.json.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = SerializationModule.class)
public abstract class JsonModule {
  private JsonModule() {}

  @Provides
  @Format(Name.CONFLUENT_JSON)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Confluent Serializer<SensorStateJson> serializer,
      @Confluent Deserializer<SensorStateJson> deserializer,
      BiMapper<SensorState, SensorStateJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.CONFLUENT_JSON)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Confluent Serializer<SensorStateDurationJson> serializer,
      @Confluent Deserializer<SensorStateDurationJson> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
