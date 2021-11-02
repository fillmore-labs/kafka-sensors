package com.fillmore_labs.kafka.sensors.serde.mixin;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.mixin.serialization.MixIn;
import com.fillmore_labs.kafka.sensors.serde.mixin.serialization.SerializationModule;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = SerializationModule.class)
public abstract class MixInModule {
  public static final String MIXIN = "mixin";

  private MixInModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(MIXIN)
  /* package */ static String encoding() {
    return "json";
  }

  @Provides
  @Named(MIXIN)
  /* package */ static Serde<Reading> readingSerde(
      @MixIn Serializer<Reading> serializer, @MixIn Deserializer<Reading> deserializer) {
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Provides
  @Named(MIXIN)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @MixIn Serializer<SensorState> serializer, @MixIn Deserializer<SensorState> deserializer) {
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Provides
  @Named(MIXIN)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @MixIn Serializer<StateDuration> serializer,
      @MixIn Deserializer<StateDuration> deserializer) {
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Binds
  @IntoMap
  @StringKey(MIXIN)
  /* package */ abstract Serde<Reading> mixinReading(@Named(MIXIN) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(MIXIN)
  /* package */ abstract Serde<SensorState> mixin(@Named(MIXIN) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(MIXIN)
  /* package */ abstract Serde<StateDuration> mixinDuration(
      @Named(MIXIN) Serde<StateDuration> serde);
}
