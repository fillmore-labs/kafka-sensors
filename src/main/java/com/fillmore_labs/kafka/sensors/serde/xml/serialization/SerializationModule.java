package com.fillmore_labs.kafka.sensors.serde.xml.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.xml.MarshallerPool;
import com.fillmore_labs.kafka.sensors.serde.serializer.xml.UnmarshallerPool;
import com.fillmore_labs.kafka.sensors.serde.serializer.xml.XmlDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.xml.XmlSerializer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  /* package */ static Serializer<ReadingXml> readingSerializer(MarshallerPool marshallerPool) {
    return new XmlSerializer<>(marshallerPool);
  }

  @Provides
  /* package */ static Deserializer<ReadingXml> readingDeserializer(
      UnmarshallerPool unmarshallerPool) {
    return new XmlDeserializer<>(unmarshallerPool, ReadingXml.class);
  }

  @Provides
  /* package */ static Serializer<SensorStateXml> sensorStateSerializer(
      MarshallerPool marshallerPool) {
    return new XmlSerializer<>(marshallerPool);
  }

  @Provides
  /* package */ static Deserializer<SensorStateXml> sensorStateDeserializer(
      UnmarshallerPool unmarshallerPool) {
    return new XmlDeserializer<>(unmarshallerPool, SensorStateXml.class);
  }

  @Provides
  /* package */ static Serializer<StateDurationXml> stateDurationSerializer(
      MarshallerPool marshallerPool) {
    return new XmlSerializer<>(marshallerPool);
  }

  @Provides
  /* package */ static Deserializer<StateDurationXml> stateDurationDeserializer(
      UnmarshallerPool unmarshallerPool) {
    return new XmlDeserializer<>(unmarshallerPool, StateDurationXml.class);
  }

  @Provides
  @Singleton
  /* package */ static JAXBContext jaxbContext() {
    try {
      return JAXBContext.newInstance(
          ReadingXml.class, SensorStateXml.class, StateDurationXml.class);
    } catch (JAXBException e) {
      throw new IllegalStateException("Can't initinalize JAXB context", e);
    }
  }
}
