package com.fillmore_labs.kafka.sensors.serde.confluent.json;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.json.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateDurationJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
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

@Module(includes = SerializationModule.class)
public abstract class JsonModule {
  public static final String CONFLUENT_JSON = "confluent-json";

  private JsonModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_JSON)
  /* package */ static String encoding() {
    return "confluent/json";
  }

  @Provides
  @Named(CONFLUENT_JSON)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Confluent Serializer<SensorStateJson> serializer,
      @Confluent Deserializer<SensorStateJson> deserializer,
      BiMapper<SensorState, SensorStateJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_JSON)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Confluent Serializer<SensorStateDurationJson> serializer,
      @Confluent Deserializer<SensorStateDurationJson> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_JSON)
  /* package */ abstract Serde<SensorState> confluentJson(
      @Named(CONFLUENT_JSON) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_JSON)
  /* package */ abstract Serde<SensorStateDuration> confluentJsonDuration(
      @Named(CONFLUENT_JSON) Serde<SensorStateDuration> serde);
}
