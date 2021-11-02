package com.fillmore_labs.kafka.sensors.serde.confluent.proto;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.proto.serialization.SerializationModule;
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

@Module(includes = SerializationModule.class)
public abstract class ConfluentProtoModule {
  public static final String CONFLUENT_PROTO = "confluent-proto";

  private ConfluentProtoModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_PROTO)
  /* package */ static String encoding() {
    return "confluent/protobuf";
  }

  @Provides
  @Named(CONFLUENT_PROTO)
  /* package */ static Serde<Reading> readingSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.proto.v1.Reading> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.proto.v1.Reading> deserializer,
      BiMapper<Reading, com.fillmore_labs.kafka.sensors.proto.v1.Reading> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_PROTO)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.proto.v1.SensorState> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.proto.v1.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.proto.v1.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_PROTO)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.proto.v1.StateDuration> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.proto.v1.StateDuration> deserializer,
      BiMapper<StateDuration, com.fillmore_labs.kafka.sensors.proto.v1.StateDuration> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_PROTO)
  /* package */ abstract Serde<Reading> confluentProtoReading(
      @Named(CONFLUENT_PROTO) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_PROTO)
  /* package */ abstract Serde<SensorState> confluentProto(
      @Named(CONFLUENT_PROTO) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_PROTO)
  /* package */ abstract Serde<StateDuration> confluentProtoDuration(
      @Named(CONFLUENT_PROTO) Serde<StateDuration> serde);
}
