package com.fillmore_labs.kafka.sensors.serde.avro.generic;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.Avro;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateDurationSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SerializationModule;
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
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class GenericModule {
  public static final String AVRO_GENERIC = "avro-generic";

  private GenericModule() {}

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateSchema() {
    return SensorStateSchema.SCHEMA;
  }

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateDurationSchema() {
    return SensorStateDurationSchema.SCHEMA;
  }

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(AVRO_GENERIC)
  /* package */ static String encoding() {
    return "avro";
  }

  @Provides
  @Named(AVRO_GENERIC)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Avro.SensorState Serializer<GenericRecord> serializer,
      @Avro.SensorState Deserializer<GenericRecord> deserializer,
      BiMapper<SensorState, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(AVRO_GENERIC)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Avro.SensorStateDuration Serializer<GenericRecord> serializer,
      @Avro.SensorStateDuration Deserializer<GenericRecord> deserializer,
      BiMapper<SensorStateDuration, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(AVRO_GENERIC)
  /* package */ abstract Serde<SensorState> avroGeneric(
      @Named(AVRO_GENERIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_GENERIC)
  /* package */ abstract Serde<SensorStateDuration> avroGenericDuration(
      @Named(AVRO_GENERIC) Serde<SensorStateDuration> serde);
}
