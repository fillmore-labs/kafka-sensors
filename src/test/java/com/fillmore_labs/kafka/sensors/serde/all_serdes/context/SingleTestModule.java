package com.fillmore_labs.kafka.sensors.serde.all_serdes.context;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import dagger.Module;
import dagger.Provides;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@Module
/* package */ abstract class SingleTestModule {
  private SingleTestModule() {}

  @Provides
  /* package */ static Serializer<Event> serializerEvent(
      Formats formats, Map<String, Serde<Event>> serdeMap) {
    var serde = serdeMap.get(formats.serialization());
    if (serde == null) {
      throw new IllegalArgumentException(
          String.format("Unknown format: %s for Event", formats.serialization()));
    }
    return serde.serializer();
  }

  @Provides
  /* package */ static Deserializer<Event> deserializerEvent(
      Formats formats, Map<String, Serde<Event>> serdeMap) {
    var serde = serdeMap.get(formats.deserialization());
    if (serde == null) {
      throw new IllegalArgumentException(
          String.format("Unknown format: %s for Event", formats.deserialization()));
    }
    return serde.deserializer();
  }

  @Provides
  /* package */ static Serializer<SensorState> serializer(
      Formats formats, Map<String, Serde<SensorState>> serdeMap) {
    var serde = serdeMap.get(formats.serialization());
    if (serde == null) {
      throw new IllegalArgumentException(
          String.format("Unknown format: %s for SensorState", formats.serialization()));
    }
    return serde.serializer();
  }

  @Provides
  /* package */ static Deserializer<SensorState> deserializer(
      Formats formats, Map<String, Serde<SensorState>> serdeMap) {
    var serde = serdeMap.get(formats.deserialization());
    if (serde == null) {
      throw new IllegalArgumentException(
          String.format("Unknown format: %s for SensorState", formats.deserialization()));
    }
    return serde.deserializer();
  }

  @Provides
  /* package */ static Serializer<StateDuration> serializerDuration(
      Formats formats, Map<String, Serde<StateDuration>> serdeMap) {
    var serde = serdeMap.get(formats.serialization());
    if (serde == null) {
      throw new IllegalArgumentException(
          String.format("Unknown format: %s for StateDuration", formats.serialization()));
    }
    return serde.serializer();
  }

  @Provides
  /* package */ static Deserializer<StateDuration> deserializerDuration(
      Formats formats, Map<String, Serde<StateDuration>> serdeMap) {
    var serde = serdeMap.get(formats.deserialization());
    if (serde == null) {
      throw new IllegalArgumentException(
          String.format("Unknown format: %s for StateDuration", formats.deserialization()));
    }
    return serde.deserializer();
  }

  @SuppressWarnings("UnusedVariable")
  public record Formats(String serialization, String deserialization) {}
}
