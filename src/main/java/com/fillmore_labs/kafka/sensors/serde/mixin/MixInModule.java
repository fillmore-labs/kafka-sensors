package com.fillmore_labs.kafka.sensors.serde.mixin;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
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
  /* package */ static String encodingText() {
    return "json";
  }

  @Provides
  @Named(MIXIN)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @MixIn Serializer<SensorState> serializer, @MixIn Deserializer<SensorState> deserializer) {
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Provides
  @Named(MIXIN)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @MixIn Serializer<SensorStateDuration> serializer,
      @MixIn Deserializer<SensorStateDuration> deserializer) {
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Binds
  @IntoMap
  @StringKey(MIXIN)
  /* package */ abstract Serde<SensorState> mixin(@Named(MIXIN) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(MIXIN)
  /* package */ abstract Serde<SensorStateDuration> mixinDuration(
      @Named(MIXIN) Serde<SensorStateDuration> serde);
}
