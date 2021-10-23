package com.fillmore_labs.kafka.sensors.serde.confluent.specific_faster;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
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

@Module
public abstract class ConfluentSpecificFasterModule {
  public static final String CONFLUENT_SPECIFIC_FASTER = "confluent-specific-faster";

  private ConfluentSpecificFasterModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_SPECIFIC_FASTER)
  /* package */ static String encodingDirect() {
    return "confluent/avro";
  }

  @Provides
  @Named(CONFLUENT_SPECIFIC_FASTER)
  /* package */ static Serde<SensorState> sensorStateDirectSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.SensorState> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorState> deserializer,
      @Named("faster")
          BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_SPECIFIC_FASTER)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationDirectSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> serializer,
      @Confluent
          Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> deserializer,
      @Named("faster")
          BiMapper<SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration>
              mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_SPECIFIC_FASTER)
  /* package */ abstract Serde<SensorState> confluentDirect(
      @Named(CONFLUENT_SPECIFIC_FASTER) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_SPECIFIC_FASTER)
  /* package */ abstract Serde<SensorStateDuration> confluentDirectDuration(
      @Named(CONFLUENT_SPECIFIC_FASTER) Serde<SensorStateDuration> serde);
}
