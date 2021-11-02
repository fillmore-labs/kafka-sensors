package com.fillmore_labs.kafka.sensors.serde.proto;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.proto.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.proto.serialization.SerializationModule;
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

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class ProtoModule {
  public static final String PROTO = "proto";

  private ProtoModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(PROTO)
  /* package */ static String encoding() {
    return "protobuf";
  }

  @Provides
  @Named(PROTO)
  /* package */ static Serde<Reading> readingSerde(
      Serializer<com.fillmore_labs.kafka.sensors.proto.v1.Reading> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.proto.v1.Reading> deserializer,
      BiMapper<Reading, com.fillmore_labs.kafka.sensors.proto.v1.Reading> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(PROTO)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<com.fillmore_labs.kafka.sensors.proto.v1.SensorState> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.proto.v1.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.proto.v1.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(PROTO)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      Serializer<com.fillmore_labs.kafka.sensors.proto.v1.StateDuration> serializer,
      Deserializer<com.fillmore_labs.kafka.sensors.proto.v1.StateDuration> deserializer,
      BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.proto.v1.StateDuration> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(PROTO)
  /* package */ abstract Serde<Reading> protoReading(@Named(PROTO) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(PROTO)
  /* package */ abstract Serde<SensorState> proto(@Named(PROTO) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(PROTO)
  /* package */ abstract Serde<StateDuration> protoDuration(
      @Named(PROTO) Serde<StateDuration> serde);
}
