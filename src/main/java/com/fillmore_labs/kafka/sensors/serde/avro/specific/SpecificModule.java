package com.fillmore_labs.kafka.sensors.serde.avro.specific;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.avro.specific.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import org.apache.avro.Schema;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class SpecificModule {
  public static final String AVRO_SPECIFIC = "avro-specific";

  private SpecificModule() {}

  @Provides
  @IntoSet
  /* package */ static Schema evenSchema() {
    return com.fillmore_labs.kafka.sensors.avro.Reading.getClassSchema();
  }

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateSchema() {
    return com.fillmore_labs.kafka.sensors.avro.SensorState.getClassSchema();
  }

  @Provides
  @IntoSet
  /* package */ static Schema stateDurationSchema() {
    return com.fillmore_labs.kafka.sensors.avro.StateDuration.getClassSchema();
  }

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(AVRO_SPECIFIC)
  /* package */ static String encoding() {
    return "avro";
  }

  @Provides
  @Named(AVRO_SPECIFIC)
  /* package */ static Serde<Reading> readingSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.Reading> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.Reading> deserializer,
      BiMapper<Reading, com.fillmore_labs.kafka.sensors.avro.Reading> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(AVRO_SPECIFIC)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.SensorState> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(AVRO_SPECIFIC)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.StateDuration> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.StateDuration> deserializer,
      BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.avro.StateDuration> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(AVRO_SPECIFIC)
  /* package */ abstract Serde<Reading> avroSpecificReading(
      @Named(AVRO_SPECIFIC) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_SPECIFIC)
  /* package */ abstract Serde<SensorState> avroSpecific(
      @Named(AVRO_SPECIFIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_SPECIFIC)
  /* package */ abstract Serde<StateDuration> avroSpecificDuration(
      @Named(AVRO_SPECIFIC) Serde<StateDuration> serde);
}
