package com.fillmore_labs.kafka.sensors.serde.confluent.reflect;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.ReadingReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.StateDurationReflect;
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
public abstract class ConfluentReflectModule {
  public static final String CONFLUENT_REFLECT = "confluent-reflect";

  private ConfluentReflectModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_REFLECT)
  /* package */ static String encoding() {
    return "confluent/avro";
  }

  @Provides
  @Named(CONFLUENT_REFLECT)
  /* package */ static Serde<Reading> readingSerde(
      Serializer<ReadingReflect> serializer,
      Deserializer<ReadingReflect> deserializer,
      BiMapper<Reading, ReadingReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_REFLECT)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<SensorStateReflect> serializer,
      Deserializer<SensorStateReflect> deserializer,
      BiMapper<SensorState, SensorStateReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_REFLECT)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      Serializer<StateDurationReflect> serializer,
      Deserializer<StateDurationReflect> deserializer,
      BiMapper<StateDuration, StateDurationReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_REFLECT)
  /* package */ abstract Serde<Reading> confluentReflectReading(
      @Named(CONFLUENT_REFLECT) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_REFLECT)
  /* package */ abstract Serde<SensorState> confluentReflect(
      @Named(CONFLUENT_REFLECT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_REFLECT)
  /* package */ abstract Serde<StateDuration> confluentReflectDuration(
      @Named(CONFLUENT_REFLECT) Serde<StateDuration> serde);
}
