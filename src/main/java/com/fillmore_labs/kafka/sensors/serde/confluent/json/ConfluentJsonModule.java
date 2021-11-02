package com.fillmore_labs.kafka.sensors.serde.confluent.json;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.json.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.ReadingJson;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.SensorStateJson;
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

@Module(includes = SerializationModule.class)
public abstract class ConfluentJsonModule {
  public static final String CONFLUENT_JSON = "confluent-json";

  private ConfluentJsonModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_JSON)
  /* package */ static String encoding() {
    return "confluent/json";
  }

  @Provides
  @Named(CONFLUENT_JSON)
  /* package */ static Serde<Reading> readingSerde(
      @Confluent Serializer<ReadingJson> serializer,
      @Confluent Deserializer<ReadingJson> deserializer,
      BiMapper<Reading, ReadingJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
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
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @Confluent Serializer<StateWithDurationJson> serializer,
      @Confluent Deserializer<StateWithDurationJson> deserializer,
      BiMapper<StateDuration, StateWithDurationJson> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_JSON)
  /* package */ abstract Serde<Reading> readingJson(@Named(CONFLUENT_JSON) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_JSON)
  /* package */ abstract Serde<SensorState> confluentJson(
      @Named(CONFLUENT_JSON) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_JSON)
  /* package */ abstract Serde<StateDuration> confluentJsonDuration(
      @Named(CONFLUENT_JSON) Serde<StateDuration> serde);
}
