package com.fillmore_labs.kafka.sensors.serde.confluent.reflect;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateDurationReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SensorStateReflect;
import com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization.SerializationModule;
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
public abstract class ReflectModule {
  public static final String CONFLUENT_REFLECT = "confluent-reflect";

  private ReflectModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(CONFLUENT_REFLECT)
  /* package */ static String encoding() {
    return "confluent/avro";
  }

  @Provides
  @Named(CONFLUENT_REFLECT)
  /* package */ static Serde<SensorState> sensorStateSerde(
      Serializer<SensorStateReflect> serializer,
      Deserializer<SensorStateReflect> deserializer,
      BiMapper<SensorState, SensorStateReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(CONFLUENT_REFLECT)
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      Serializer<SensorStateDurationReflect> serializer,
      Deserializer<SensorStateDurationReflect> deserializer,
      BiMapper<SensorStateDuration, SensorStateDurationReflect> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_REFLECT)
  /* package */ abstract Serde<SensorState> confluentReflect(
      @Named(CONFLUENT_REFLECT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(CONFLUENT_REFLECT)
  /* package */ abstract Serde<SensorStateDuration> confluentReflectDuration(
      @Named(CONFLUENT_REFLECT) Serde<SensorStateDuration> serde);
}
