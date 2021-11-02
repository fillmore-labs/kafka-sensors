package com.fillmore_labs.kafka.sensors.serde.thrift;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import com.fillmore_labs.kafka.sensors.serde.thrift.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.thrift.serialization.SerializationModule;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class ThriftModule {
  public static final String THRIFT = "thrift";

  private ThriftModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(THRIFT)
  /* package */ static String encoding() {
    return "thrift";
  }

  @Provides
  @Named(THRIFT)
  /* package */ static Serde<Reading> readingSerde(
      Serializer<com.fillmore_labs.kafka.sensors.thrift.v1.Reading> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.thrift.v1.Reading> deserializer,
      BiMapper<Reading, com.fillmore_labs.kafka.sensors.thrift.v1.Reading> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(THRIFT)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<com.fillmore_labs.kafka.sensors.thrift.v1.SensorState> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.thrift.v1.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.thrift.v1.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(THRIFT)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      Serializer<com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration> deserializer,
      BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.thrift.v1.StateDuration> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(THRIFT)
  /* package */ abstract Serde<Reading> thriftReading(@Named(THRIFT) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(THRIFT)
  /* package */ abstract Serde<SensorState> thrift(@Named(THRIFT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(THRIFT)
  /* package */ abstract Serde<StateDuration> thriftDuration(
      @Named(THRIFT) Serde<StateDuration> serde);
}
