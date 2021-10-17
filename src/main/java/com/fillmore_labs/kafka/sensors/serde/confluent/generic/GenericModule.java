package com.fillmore_labs.kafka.sensors.serde.confluent.generic;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.Confluent;
import com.fillmore_labs.kafka.sensors.serde.confluent.generic.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.generic.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import dagger.Module;
import dagger.Provides;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {MapperModule.class, SerializationModule.class})
public abstract class GenericModule {
  private GenericModule() {}

  @Provides
  @Format(Name.CONFLUENT_GENERIC)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Confluent.SensorState Serializer<GenericRecord> serializer,
      @Confluent.SensorState Deserializer<GenericRecord> deserializer,
      @Confluent BiMapper<SensorState, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Format(Name.CONFLUENT_GENERIC)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serde<SensorStateDuration> sensorStateDurationSerde(
      @Confluent.SensorStateDuration Serializer<GenericRecord> serializer,
      @Confluent.SensorStateDuration Deserializer<GenericRecord> deserializer,
      @Confluent BiMapper<SensorStateDuration, GenericRecord> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }
}
