package com.fillmore_labs.kafka.sensors.serde.confluent.specific;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.specific.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = SerializationModule.class)
public abstract class SpecificModule {
  private SpecificModule() {}

  @Provides
  @Format(Name.CONFLUENT_SPECIFIC)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.SensorState> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorState> deserializer,
      BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.CONFLUENT_SPECIFIC)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> serializer,
      @Confluent
          Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> deserializer,
      BiMapper<SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration>
          mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.CONFLUENT_DIRECT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateDirectSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.SensorState> serializer,
      @Confluent Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorState> deserializer,
      @Named("direct")
          BiMapper<SensorState, com.fillmore_labs.kafka.sensors.avro.SensorState> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.CONFLUENT_DIRECT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationDirectSerde(
      @Confluent Serializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> serializer,
      @Confluent
          Deserializer<com.fillmore_labs.kafka.sensors.avro.SensorStateDuration> deserializer,
      @Named("direct")
          BiMapper<SensorStateDuration, com.fillmore_labs.kafka.sensors.avro.SensorStateDuration>
              mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
