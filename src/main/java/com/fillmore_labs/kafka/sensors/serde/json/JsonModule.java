package com.fillmore_labs.kafka.sensors.serde.json;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.json.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.EventJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.StateWithDurationJson;
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
  /* package */ static Serde<Event> sensorEventSerde(
      Serializer<EventJson> serializer,
      Deserializer<EventJson> deserializer,
      BiMapper<Event, EventJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(JSON)
  /* package */ static Serde<SensorState> sensorEventWithIdSerde(
      Serializer<SensorStateJson> serializer,
      Deserializer<SensorStateJson> deserializer,
      BiMapper<SensorState, SensorStateJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(JSON)
  /* package */ static Serde<StateDuration> stateWithDurationSerde(
      Serializer<StateWithDurationJson> serializer,
      Deserializer<StateWithDurationJson> deserializer,
      BiMapper<StateDuration, StateWithDurationJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(JSON)
  /* package */ abstract Serde<Event> jsonEvent(@Named(JSON) Serde<Event> serde);

  @Binds
  @IntoMap
  @StringKey(JSON)
  /* package */ abstract Serde<SensorState> json(@Named(JSON) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(JSON)
  /* package */ abstract Serde<StateDuration> jsonDuration(@Named(JSON) Serde<StateDuration> serde);
}
