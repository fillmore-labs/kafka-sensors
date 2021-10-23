package com.fillmore_labs.kafka.sensors.serde.gson_faster;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateDurationGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.gson_faster.serialization.SerializationModule;
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

@Module(includes = {SerializationModule.class})
public abstract class GsonFasterModule {
  public static final String GSON_FASTER = "gson-faster";

  private GsonFasterModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(GSON_FASTER)
  /* package */ static String encodingFast() {
    return "json";
  }

  @Provides
  @Named(GSON_FASTER)
  /* package */ static Serde<SensorState> sensorStateFastSerde(
      Serializer<SensorStateGson> serializer,
      @Named("faster") Deserializer<SensorStateGson> deserializer,
      BiMapper<SensorState, SensorStateGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(GSON_FASTER)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationFastSerde(
      Serializer<SensorStateDurationGson> serializer,
      @Named("faster") Deserializer<SensorStateDurationGson> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationGson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(GSON_FASTER)
  /* package */ abstract Serde<SensorState> gsonFast(@Named(GSON_FASTER) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(GSON_FASTER)
  /* package */ abstract Serde<SensorStateDuration> gsonFastDuration(
      @Named(GSON_FASTER) Serde<SensorStateDuration> serde);
}
