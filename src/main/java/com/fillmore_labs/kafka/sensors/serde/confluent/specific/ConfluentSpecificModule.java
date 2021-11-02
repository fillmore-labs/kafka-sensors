package com.fillmore_labs.kafka.sensors.serde.confluent.specific;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.specific.serialization.SerializationModule;
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
public abstract class ConfluentSpecificModule {
  public static final String CONFLUENT_SPECIFIC = "confluent-specific";

  private ConfluentSpecificModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_SPECIFIC)
  /* package */ static String encoding() {
    return "confluent/avro";
  }

  @Provides
  @Named(CONFLUENT_SPECIFIC)
  /* package */ static Serde<Reading> readingStateSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.Reading> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.avro.Reading> deserializer,
      BiMapper<Reading, com.fillmore_labs.kafka.sensors.avro.Reading> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_SPECIFIC)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.SensorState> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_SPECIFIC)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.StateDuration> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.avro.StateDuration> deserializer,
      BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.avro.StateDuration> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_SPECIFIC)
  /* package */ abstract Serde<Reading> confluentSpecificReading(
      @Named(CONFLUENT_SPECIFIC) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_SPECIFIC)
  /* package */ abstract Serde<SensorState> confluentSpecific(
      @Named(CONFLUENT_SPECIFIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_SPECIFIC)
  /* package */ abstract Serde<StateDuration> confluentSpecificDuration(
      @Named(CONFLUENT_SPECIFIC) Serde<StateDuration> serde);
}
