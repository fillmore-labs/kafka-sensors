package com.fillmore_labs.kafka.sensors.serde.ion;

import com.fillmore_labs.kafka.sensors.model.Event;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.EventIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SensorStateIon;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.SerializationModule.Text;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.StateDurationIon;
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

@Module
public abstract class IonTextModule {
  public static final String ION_TEXT = "ion-text";

  private IonTextModule() {}

  @Provides
  @IntoMap
  @Named("encoding")
  @StringKey(ION_TEXT)
  /* package */ static String encoding() {
    return "ion";
  }

  @Provides
  @Named(ION_TEXT)
  /* package */ static Serde<Event> evenSerde(
      @Text Serializer<EventIon> serializer,
      Deserializer<EventIon> deserializer,
      BiMapper<Event, EventIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(ION_TEXT)
  /* package */ static Serde<SensorState> sensorStateSerde(
      @Text Serializer<SensorStateIon> serializer,
      Deserializer<SensorStateIon> deserializer,
      BiMapper<SensorState, SensorStateIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Provides
  @Named(ION_TEXT)
  /* package */ static Serde<StateDuration> stateDurationSerde(
      @Text Serializer<StateDurationIon> serializer,
      Deserializer<StateDurationIon> deserializer,
      BiMapper<StateDuration, StateDurationIon> mapper) {
    return MappedSerdes.serdeFrom(serializer, deserializer, mapper);
  }

  @Binds
  @IntoMap
  @StringKey(ION_TEXT)
  /* package */ abstract Serde<Event> ionTextEvent(@Named(ION_TEXT) Serde<Event> serde);

  @Binds
  @IntoMap
  @StringKey(ION_TEXT)
  /* package */ abstract Serde<SensorState> ionText(@Named(ION_TEXT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @StringKey(ION_TEXT)
  /* package */ abstract Serde<StateDuration> ionTextDuration(
      @Named(ION_TEXT) Serde<StateDuration> serde);
}
