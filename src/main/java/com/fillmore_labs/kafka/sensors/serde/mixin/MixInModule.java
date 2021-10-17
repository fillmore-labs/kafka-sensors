package com.fillmore_labs.kafka.sensors.serde.mixin;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.mixin.serialization.MixIn;
import com.fillmore_labs.kafka.sensors.serde.mixin.serialization.SerializationModule;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = SerializationModule.class)
public abstract class MixInModule {
  private MixInModule() {}

  @Provides
  @Format(Name.MIXIN)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      @MixIn Serializer<SensorState> serializer, @MixIn Deserializer<SensorState> deserializer) {
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Provides
  @Format(Name.MIXIN)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @MixIn Serializer<SensorStateDuration> serializer,
      @MixIn Deserializer<SensorStateDuration> deserializer) {
    return Serdes.serdeFrom(serializer, deserializer);
  }
}
