package com.fillmore_labs.kafka.sensors.serde.avro.specific;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.specific.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.avro.specific.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import javax.inject.Named;
import org.apache.avro.Schema;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class SpecificModule {
  private SpecificModule() {}

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateSchema() {
    return com.fillmore_labs.kafka.sensors.avro.SensorState.getClassSchema();
  }

  @Provides
  @IntoSet
  /* package */ static Schema sensorStateDurationSchema() {
    return com.fillmore_labs.kafka.sensors.avro.SensorStateDuration.getClassSchema();
  }

  @Provides
  @Format(Name.AVRO_SPECIFIC)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.SensorState> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.AVRO_SPECIFIC)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> deserializer,
      BiMapper<SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration>
          mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.AVRO_DIRECT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateDirectSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.SensorState> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorState> deserializer,
      @Named("direct")
          BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.AVRO_DIRECT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationDirectSerde(
      Serializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> deserializer,
      @Named("direct")
          BiMapper<SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration>
              mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
