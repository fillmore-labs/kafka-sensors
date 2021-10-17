package com.fillmore_labs.kafka.sensors.serde.confluent.reflect;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateDurationReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class ReflectModule {
  private ReflectModule() {}

  @Provides
  @Format(Name.CONFLUENT_REFLECT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<SensorStateReflect> serializer,
      Deserializer<SensorStateReflect> deserializer,
      BiMapper<SensorState, SensorStateReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.CONFLUENT_REFLECT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      Serializer<SensorStateDurationReflect> serializer,
      Deserializer<SensorStateDurationReflect> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
