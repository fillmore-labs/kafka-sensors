package com.fillmore_labs.kafka.sensors.serde.avro.specific_faster;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.specific_faster.mapper.MapperModule;
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

@Module(includes = MapperModule.class)
public abstract class SpecificFasterModule {
  public static final String AVRO_SPECIFIC_FASTER = "avro-specific-faster";

  private SpecificFasterModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(AVRO_SPECIFIC_FASTER)
  /* package */ static String encoding() {
    return "avro";
  }

  @Provides
  @Named(AVRO_SPECIFIC_FASTER)
  /* package */ static Serde<Event> eventSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.Event> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.Event> deserializer,
      @Named("faster") BiMapper<Event, com.fillmore_labs.kafka.sensors.avro.Event> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(AVRO_SPECIFIC_FASTER)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.SensorState> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorState> deserializer,
      @Named("faster")
          BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(AVRO_SPECIFIC_FASTER)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.StateDuration> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.StateDuration> deserializer,
      @Named("faster")
          BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.avro.StateDuration> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(AVRO_SPECIFIC_FASTER)
  /* package */ abstract Serde<Event> avroEvent(@Named(AVRO_SPECIFIC_FASTER) Serde<Event> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_SPECIFIC_FASTER)
  /* package */ abstract Serde<SensorState> avro(
      @Named(AVRO_SPECIFIC_FASTER) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_SPECIFIC_FASTER)
  /* package */ abstract Serde<StateDuration> avroDuration(
      @Named(AVRO_SPECIFIC_FASTER) Serde<StateDuration> serde);
}
