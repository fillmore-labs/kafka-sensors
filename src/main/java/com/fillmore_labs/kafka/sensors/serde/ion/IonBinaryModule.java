package com.fillmore_labs.kafka.sensors.serde.ion;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.ReadingIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SerializationModule.Binary;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.StateDurationIon;
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
public abstract class IonBinaryModule {
  public static final String ION_BINARY = "ion-binary";

  private IonBinaryModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(ION_BINARY)
  /* package */ static String encoding() {
    return "ion";
  }

  @Provides
  @Named(ION_BINARY)
  /* package */ static Serde<Reading> evenSerde(
      @Binary Serializer<ReadingIon> serializer,
      Deserializer<ReadingIon> deserializer,
      BiMapper<Reading, ReadingIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(ION_BINARY)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Binary Serializer<SensorStateIon> serializer,
      Deserializer<SensorStateIon> deserializer,
      BiMapper<SensorState, SensorStateIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(ION_BINARY)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @Binary Serializer<StateDurationIon> serializer,
      Deserializer<StateDurationIon> deserializer,
      BiMapper<StateDuration, StateDurationIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(ION_BINARY)
  /* package */ abstract Serde<Reading> ionBinaryReading(@Named(ION_BINARY) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(ION_BINARY)
  /* package */ abstract Serde<SensorState> ionBinary(@Named(ION_BINARY) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(ION_BINARY)
  /* package */ abstract Serde<StateDuration> ionBinaryDuration(
      @Named(ION_BINARY) Serde<StateDuration> serde);
}
