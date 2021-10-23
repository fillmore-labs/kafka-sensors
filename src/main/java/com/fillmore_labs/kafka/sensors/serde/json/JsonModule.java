package com.fillmore_labs.kafka.sensors.serde.json;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.json.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SerializationModule;
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
public abstract class JsonModule {
  public static final String JSON = "json";

  private JsonModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(JSON)
  /* package */ static String encoding() {
    return "json";
  }

  @Provides
  @Named(JSON)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<SensorStateJson> serializer,
      Deserializer<SensorStateJson> deserializer,
      BiMapper<SensorState, SensorStateJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(JSON)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      Serializer<SensorStateDurationJson> serializer,
      Deserializer<SensorStateDurationJson> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(JSON)
  /* package */ abstract Serde<SensorState> json(@Named(JSON) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(JSON)
  /* package */ abstract Serde<SensorStateDuration> jsonDuration(
      @Named(JSON) Serde<SensorStateDuration> serde);
}
