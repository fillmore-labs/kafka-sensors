package com.fillmore_labs.kafka.sensors.serde.ion.serialization;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.amazon.ion.system.IonBinaryWriterBuilder;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.fillmore_labs.kafka.sensors.serde.serializer.ion.IonDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.ion.IonSerializer;
import dagger.Module;
import dagger.Provides;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

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
  @Binary
  /* package */ static Serializer<ReadingIon> readingSerializerBinary(
      IonBinaryWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeReading);
  }

  @Provides
  @Text
  /* package */ static Serializer<ReadingIon> readingSerializerText(
      IonTextWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeReading);
  }

  @Provides
  /* package */ static Deserializer<ReadingIon> readingDeserializer(
      IonReaderBuilder readerBuilder) {
    return new IonDeserializer<>(readerBuilder, IonSerializerHelper::deserializeReading);
  }

  @Provides
  @Binary
  /* package */ static Serializer<SensorStateIon> sensorStateSerializerBinary(
      IonBinaryWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeSensorState);
  }

  @Provides
  @Text
  /* package */ static Serializer<SensorStateIon> sensorStateSerializerText(
      IonTextWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeSensorState);
  }

  @Provides
  /* package */ static Deserializer<SensorStateIon> sensorStateDeserializer(
      IonReaderBuilder readerBuilder) {
    return new IonDeserializer<>(readerBuilder, IonSerializerHelper::deserializeSensorState);
  }

  @Provides
  @Binary
  /* package */ static Serializer<StateDurationIon> stateDurationSerializerBinary(
      IonBinaryWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeStateDuration);
  }

  @Provides
  @Text
  /* package */ static Serializer<StateDurationIon> stateDurationSerializerText(
      IonTextWriterBuilder writerBuilder) {
    return new IonSerializer<>(writerBuilder, IonSerializerHelper::serializeStateDuration);
  }

  @Provides
  /* package */ static Deserializer<StateDurationIon> stateDurationDeserializer(
      IonReaderBuilder readerBuilder) {
    return new IonDeserializer<>(readerBuilder, IonSerializerHelper::deserializeStateDuration);
  }

  @Qualifier
  @Documented
  @Retention(RUNTIME)
  public @interface Binary {}

  @Qualifier
  @Documented
  @Retention(RUNTIME)
  public @interface Text {}
}
