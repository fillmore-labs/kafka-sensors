package com.fillmore_labs.kafka.sensors.serde.ion;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.ion.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.IonSerializationModule;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.IonSerializationModule.Binary;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.IonSerializationModule.Text;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateDurationIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateIon;
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

@Module(includes = {IonSerializationModule.class, MapperModule.class})
public abstract class IonModule {
  public static final String ION_BINARY = "ion-binary";
  public static final String ION_TEXT = "ion-text";

  private IonModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(ION_BINARY)
  /* package */ static String encoding() {
    return "ion";
  }

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(ION_TEXT)
  /* package */ static String encodingText() {
    return "ion";
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
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Binary Serializer<SensorStateDurationIon> serializer,
      Deserializer<SensorStateDurationIon> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(ION_TEXT)
  /* package */ static Serde<SensorState> sensorStateSerdeText(
      @Text Serializer<SensorStateIon> serializer,
      Deserializer<SensorStateIon> deserializer,
      BiMapper<SensorState, SensorStateIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(ION_TEXT)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerdeText(
      @Text Serializer<SensorStateDurationIon> serializer,
      Deserializer<SensorStateDurationIon> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(ION_BINARY)
  /* package */ abstract Serde<SensorState> ionBinary(@Named(ION_BINARY) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(ION_BINARY)
  /* package */ abstract Serde<SensorStateDuration> ionBinaryDuration(
      @Named(ION_BINARY) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @StringKey(ION_TEXT)
  /* package */ abstract Serde<SensorState> ionText(@Named(ION_TEXT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(ION_TEXT)
  /* package */ abstract Serde<SensorStateDuration> ionTextDuration(
      @Named(ION_TEXT) Serde<SensorStateDuration> serde);
}
