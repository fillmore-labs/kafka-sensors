package com.fillmore_labs.kafka.sensors.serde.avro.generic;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.Avro;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.EventSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SensorStateSchema;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.avro.generic.serialization.StateDurationSchema;
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
  /* package */ static Schema eventSchema() {
    return EventSchema.SCHEMA;
  }

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateSchema() {
    return SensorStateSchema.SCHEMA;
  }

  @Provides
  @IntoSet
  /* package */ static Schema stateDurationSchema() {
    return StateDurationSchema.SCHEMA;
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
  /* package */ static Serde<Event> eventSerde(
      @Avro.Event Serializer<GenericRecord> serializer,
      @Avro.Event Deserializer<GenericRecord> deserializer,
      BiMapper<Event, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
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
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @Avro.StateDuration Serializer<GenericRecord> serializer,
      @Avro.StateDuration Deserializer<GenericRecord> deserializer,
      BiMapper<StateDuration, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(AVRO_GENERIC)
  /* package */ abstract Serde<Event> avroGenericEvent(@Named(AVRO_GENERIC) Serde<Event> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_GENERIC)
  /* package */ abstract Serde<SensorState> avroGeneric(
      @Named(AVRO_GENERIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(AVRO_GENERIC)
  /* package */ abstract Serde<StateDuration> avroGenericDuration(
      @Named(AVRO_GENERIC) Serde<StateDuration> serde);
}
