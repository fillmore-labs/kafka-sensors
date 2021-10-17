package com.fillmore_labs.kafka.sensors.serde.gson;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.gson.mapper.GsonMapperModule;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateDurationGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, GsonMapperModule.class})
public abstract class GsonModule {
  private GsonModule() {}

  @Provides
  @Format(Name.GSON)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<SensorStateGson> serializer,
      Deserializer<SensorStateGson> deserializer,
      BiMapper<SensorState, SensorStateGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.GSON)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      Serializer<SensorStateDurationGson> serializer,
      Deserializer<SensorStateDurationGson> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.GSON_FAST)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateFastSerde(
      Serializer<SensorStateGson> serializer,
      @Named("fast") Deserializer<SensorStateGson> deserializer,
      BiMapper<SensorState, SensorStateGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.GSON_FAST)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationFastSerde(
      Serializer<SensorStateDurationGson> serializer,
      @Named("fast") Deserializer<SensorStateDurationGson> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
