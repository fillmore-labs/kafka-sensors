package com.fillmore_labs.kafka.sensors.serde.ion;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.ion.mapper.IonMapperModule;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.IonSerializationModule;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateDurationIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateIon;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {IonSerializationModule.class, IonMapperModule.class})
public abstract class IonModule {
  private IonModule() {}

  @Provides
  @Format(Name.ION_BINARY)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Format(Name.ION_BINARY) Serializer<SensorStateIon> serializer,
      Deserializer<SensorStateIon> deserializer,
      BiMapper<SensorState, SensorStateIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.ION_BINARY)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Format(Name.ION_BINARY) Serializer<SensorStateDurationIon> serializer,
      Deserializer<SensorStateDurationIon> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.ION_TEXT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerdeText(
      @Format(Name.ION_TEXT) Serializer<SensorStateIon> serializer,
      Deserializer<SensorStateIon> deserializer,
      BiMapper<SensorState, SensorStateIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.ION_TEXT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerdeText(
      @Format(Name.ION_TEXT) Serializer<SensorStateDurationIon> serializer,
      Deserializer<SensorStateDurationIon> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
