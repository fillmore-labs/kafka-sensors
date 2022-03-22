package com.fillmore_labs.kafka.sensors.serde.json_iso;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.json.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.ReadingJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.StateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json_iso.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.json_iso.serialization.SerializationModule.JsonIso;
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
public abstract class JsonIsoModule {
  public static final String JSON_ISO = "json-iso";

  private JsonIsoModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(JSON_ISO)
  /* package */ static String encoding() {
    return "json-iso";
  }

  @Provides
  @Named(JSON_ISO)
  /* package */ static Serde<Reading> sensorReadingSerde(
      @JsonIso Serializer<ReadingJson> serializer,
      @JsonIso Deserializer<ReadingJson> deserializer,
      BiMapper<Reading, ReadingJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(JSON_ISO)
  /* package */ static Serde<SensorState> sensorReadingWithIdSerde(
      @JsonIso Serializer<SensorStateJson> serializer,
      @JsonIso Deserializer<SensorStateJson> deserializer,
      BiMapper<SensorState, SensorStateJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(JSON_ISO)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @JsonIso Serializer<StateDurationJson> serializer,
      @JsonIso Deserializer<StateDurationJson> deserializer,
      BiMapper<StateDuration, StateDurationJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(JSON_ISO)
  /* package */ abstract Serde<Reading> jsonReading(@Named(JSON_ISO) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(JSON_ISO)
  /* package */ abstract Serde<SensorState> json(@Named(JSON_ISO) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(JSON_ISO)
  /* package */ abstract Serde<StateDuration> jsonDuration(
      @Named(JSON_ISO) Serde<StateDuration> serde);
}
