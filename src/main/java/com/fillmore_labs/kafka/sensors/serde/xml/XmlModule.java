package com.fillmore_labs.kafka.sensors.serde.xml;

import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.MappedSerdes;
import com.fillmore_labs.kafka.sensors.serde.xml.mapper.MapperModule;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.ReadingXml;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.SensorStateXml;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.SerializationModule;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.StateDurationXml;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import jakarta.inject.Named;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module(includes = {SerializationModule.class, MapperModule.class})
public abstract class XmlModule {
  public static final String XML = "xml";

  private XmlModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(XML)
  /* package */ static String encoding() {
    return "xml";
  }

  @Provides
  @Named(XML)
  /* package */ static Serde<Reading> sensorReadingSerde(
      Serializer<ReadingXml> serializer,
      Deserializer<ReadingXml> deserializer,
      BiMapper<Reading, ReadingXml> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(XML)
  /* package */ static Serde<SensorState> sensorReadingWithIdSerde(
      Serializer<SensorStateXml> serializer,
      Deserializer<SensorStateXml> deserializer,
      BiMapper<SensorState, SensorStateXml> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(XML)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      Serializer<StateDurationXml> serializer,
      Deserializer<StateDurationXml> deserializer,
      BiMapper<StateDuration, StateDurationXml> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(XML)
  /* package */ abstract Serde<Reading> xmlReading(@Named(XML) Serde<Reading> serde);

  @Binds
  @IntoMap
  @StringKey(XML)
  /* package */ abstract Serde<SensorState> xml(@Named(XML) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(XML)
  /* package */ abstract Serde<StateDuration> xmlDuration(@Named(XML) Serde<StateDuration> serde);
}
