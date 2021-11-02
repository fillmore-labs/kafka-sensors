package com.fillmore_labs.kafka.sensors.serde.avro.reflect;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.ReadingReflect;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SensorStateReflect;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.avro.reflect.serialization.StateDurationReflect;
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
public abstract class ReflectModule {
  public static final String AVRO_REFLECT = "avro-reflect";

  private ReflectModule() {}

  @Provides
  @IntoSet
  /* package */ static Schema readingSchema() {
    return ReadingReflect.SCHEMA;
  }

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateSchema() {
    return SensorStateReflect.SCHEMA;
  }

  @Provides
  @IntoSet
  /* package */ static Schema stateDurationSchema() {
    return StateDurationReflect.SCHEMA;
  }

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(AVRO_REFLECT)
  /* package */ static String encoding() {
    return "avro";
  }

  @Provides
  @Named(AVRO_REFLECT)
  /* package */ static Serde<Reading> readingSerde(
      Serializer<ReadingReflect> serializer,
      Deserializer<ReadingReflect> deserializer,
      BiMapper<Reading, ReadingReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(AVRO_REFLECT)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<SensorStateReflect> serializer,
      Deserializer<SensorStateReflect> deserializer,
      BiMapper<SensorState, SensorStateReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(AVRO_REFLECT)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      Serializer<StateDurationReflect> serializer,
      Deserializer<StateDurationReflect> deserializer,
      BiMapper<StateDuration, StateDurationReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(AVRO_REFLECT)
  /* package */ abstract Serde<Reading> avroReflectReading(
      @Named(AVRO_REFLECT) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_REFLECT)
  /* package */ abstract Serde<SensorState> avroReflect(
      @Named(AVRO_REFLECT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_REFLECT)
  /* package */ abstract Serde<StateDuration> avroReflectDuration(
      @Named(AVRO_REFLECT) Serde<StateDuration> serde);
}
