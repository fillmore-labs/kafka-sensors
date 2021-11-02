package com.fillmore_labs.kafka.sensors.serde.gson;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.gson.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.ReadingGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.StateDurationGson;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class GsonModule {
  public static final String GSON = "gson";

  private GsonModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(GSON)
  /* package */ static String encoding() {
    return "json";
  }

  @Provides
  @Named(GSON)
  /* package */ static Serde<Reading> readingSerde(
      Serializer<ReadingGson> serializer,
      Deserializer<ReadingGson> deserializer,
      BiMapper<Reading, ReadingGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(GSON)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<SensorStateGson> serializer,
      Deserializer<SensorStateGson> deserializer,
      BiMapper<SensorState, SensorStateGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(GSON)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      Serializer<StateDurationGson> serializer,
      Deserializer<StateDurationGson> deserializer,
      BiMapper<StateDuration, StateDurationGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(GSON)
  /* package */ abstract Serde<Reading> gsonReading(@Named(GSON) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(GSON)
  /* package */ abstract Serde<SensorState> gson(@Named(GSON) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(GSON)
  /* package */ abstract Serde<StateDuration> gsonDuration(@Named(GSON) Serde<StateDuration> serde);
}
