package com.fillmore_labs.kafka.sensors.serde.confluent.generic;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper.MapperModule;
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
public abstract class GenericModule {
  public static final String CONFLUENT_GENERIC = "confluent-generic";

  private GenericModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_GENERIC)
  /* package */ static String encoding() {
    return "confluent/avro";
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
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Confluent.SensorStateDuration Serializer<GenericRecord> serializer,
      @Confluent.SensorStateDuration Deserializer<GenericRecord> deserializer,
      @Confluent BiMapper<SensorStateDuration, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_GENERIC)
  /* package */ abstract Serde<SensorState> confluentGenerict(
      @Named(CONFLUENT_GENERIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_GENERIC)
  /* package */ abstract Serde<SensorStateDuration> confluentGenerictDuration(
      @Named(CONFLUENT_GENERIC) Serde<SensorStateDuration> serde);
}
