package com.fillmore_labs.kafka.sensors.serde.proto;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.proto.mapper.ProtoMapperModule;
import com.fillmore_labs.kafka.sensors.serde.proto.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, ProtoMapperModule.class})
public abstract class ProtoModule {
  private ProtoModule() {}

  @Provides
  @Format(Name.PROTO)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<com.fillmore_labs.kafka.sensors.v1.SensorState> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.v1.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.v1.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.PROTO)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      Serializer<com.fillmore_labs.kafka.sensors.v1.SensorStateDuration> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.v1.SensorStateDuration> deserializer,
      BiMapper<SensorStateDuration, com.fillmore_labs.kafka.sensors.v1.SensorStateDuration>
          mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
