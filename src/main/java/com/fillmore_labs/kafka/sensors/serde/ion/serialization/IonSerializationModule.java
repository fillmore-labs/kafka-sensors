package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import com.amazon.ion.system.IonBinaryWriterBuilder;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.serializer.ion.IonDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.ion.IonSerializer;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class IonSerializationModule {
  private IonSerializationModule() {}

  @Provides
  /* package */ static IonBinaryWriterBuilder binaryWriterBuilder() {
    return IonBinaryWriterBuilder.standard().immutable();
  }

  @Provides
  /* package */ static IonTextWriterBuilder textWriterBuilder() {
    return IonTextWriterBuilder.standard().immutable();
  }

  @Provides
  /* package */ static IonReaderBuilder readerBuilder() {
    return IonReaderBuilder.standard().withIncrementalReadingEnabled(true).immutable();
  }

  @Provides
  @Format(Name.ION_BINARY)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateIon> sensorStateSerializerBinary(
      IonBinaryWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeSensorState);
  }

  @Provides
  @Format(Name.ION_TEXT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateIon> sensorStateSerializerText(
      IonTextWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeSensorState);
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateIon> sensorStateDeserializer(
      IonReaderBuilder readerBuilder) {
    return new IonDeserializer<>(readerBuilder, IonSerializerHelper::deserializeSensorState);
  }

  @Provides
  @Format(Name.ION_BINARY)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateDurationIon> sensorStateDurationSerializerBinary(
      IonBinaryWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeSensorStateDuration);
  }

  @Provides
  @Format(Name.ION_TEXT)
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateDurationIon> sensorStateDurationSerializerText(
      IonTextWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeSensorStateDuration);
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateDurationIon> sensorStateDurationDeserializer(
      IonReaderBuilder readerBuilder) {
    return new IonDeserializer<>(
        readerBuilder, IonSerializerHelper::deserializeSensorStateDuration);
  }
}
