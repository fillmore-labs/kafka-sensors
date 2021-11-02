package com.fillmore_labs.kafka.sensors.serde.confluent.generic;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class ConfluentGenericModule {
  public static final String CONFLUENT_GENERIC = "confluent-generic";

  private ConfluentGenericModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_GENERIC)
  /* package */ static String encoding() {
    return "confluent/avro";
  }

  @Provides
  @Named(CONFLUENT_GENERIC)
  /* package */ static Serde<Reading> readingSerde(
      @Confluent.Reading Serializer<GenericRecord> serializer,
      @Confluent.Reading Deserializer<GenericRecord> deserializer,
      @Confluent BiMapper<Reading, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_GENERIC)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Confluent.SensorState Serializer<GenericRecord> serializer,
      @Confluent.SensorState Deserializer<GenericRecord> deserializer,
      @Confluent BiMapper<SensorState, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_GENERIC)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @Confluent.StateDuration Serializer<GenericRecord> serializer,
      @Confluent.StateDuration Deserializer<GenericRecord> deserializer,
      @Confluent BiMapper<StateDuration, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_GENERIC)
  /* package */ abstract Serde<Reading> confluentGenerictReading(
      @Named(CONFLUENT_GENERIC) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_GENERIC)
  /* package */ abstract Serde<SensorState> confluentGenerict(
      @Named(CONFLUENT_GENERIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_GENERIC)
  /* package */ abstract Serde<StateDuration> confluentGenerictDuration(
      @Named(CONFLUENT_GENERIC) Serde<StateDuration> serde);
}
