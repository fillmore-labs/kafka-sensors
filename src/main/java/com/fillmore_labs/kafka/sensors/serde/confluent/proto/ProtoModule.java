package com.fillmore_labs.kafka.sensors.serde.confluent.proto;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
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
public abstract class ProtoModule {
  public static final String CONFLUENT_PROTO = "confluent-proto";

  private ProtoModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_PROTO)
  /* package */ static String encoding() {
    return "confluent/protobuf";
  }

  @Provides
  @Named(CONFLUENT_PROTO)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.v1.SensorState> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.v1.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.v1.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_PROTO)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.v1.SensorStateDuration> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.v1.SensorStateDuration> deserializer,
      BiMapper<SensorStateDuration, com.fillmore_labs.kafka.sensors.v1.SensorStateDuration>
          mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_PROTO)
  /* package */ abstract Serde<SensorState> confluentProto(
      @Named(CONFLUENT_PROTO) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_PROTO)
  /* package */ abstract Serde<SensorStateDuration> confluentProtoDuration(
      @Named(CONFLUENT_PROTO) Serde<SensorStateDuration> serde);
}
