package com.fillmore_labs.kafka.sensors.serde.all_serdes;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.avro.AvroModule;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.confluent.ConfluentModule;
import com.fillmore_labs.kafka.sensors.serde.gson.GsonModule;
import com.fillmore_labs.kafka.sensors.serde.ion.IonModule;
import com.fillmore_labs.kafka.sensors.serde.json.JsonModule;
import com.fillmore_labs.kafka.sensors.serde.mixin.MixInModule;
import com.fillmore_labs.kafka.sensors.serde.proto.ProtoModule;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import org.apache.kafka.common.serialization.Serde;

@Module(
    includes = {
      AvroModule.class,
      ConfluentModule.class,
      IonModule.class,
      JsonModule.class,
      MixInModule.class,
      ProtoModule.class,
      GsonModule.class,
    })
public abstract class AllSerdesModule {
  private AllSerdesModule() {}

  @Binds
  @IntoMap
  @NameKey(Name.AVRO_REFLECT)
  /* package */ abstract Serde<SensorState> avroReflect(
      @Format(Name.AVRO_REFLECT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.AVRO_REFLECT)
  /* package */ abstract Serde<SensorStateDuration> avroReflectDuration(
      @Format(Name.AVRO_REFLECT) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.AVRO_GENERIC)
  /* package */ abstract Serde<SensorState> avroGeneric(
      @Format(Name.AVRO_GENERIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.AVRO_GENERIC)
  /* package */ abstract Serde<SensorStateDuration> avroGenericDuration(
      @Format(Name.AVRO_GENERIC) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.AVRO_SPECIFIC)
  /* package */ abstract Serde<SensorState> avroSpecific(
      @Format(Name.AVRO_SPECIFIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.AVRO_SPECIFIC)
  /* package */ abstract Serde<SensorStateDuration> avroSpecificDuration(
      @Format(Name.AVRO_SPECIFIC) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.AVRO_DIRECT)
  /* package */ abstract Serde<SensorState> avroDirect(
      @Format(Name.AVRO_DIRECT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.AVRO_DIRECT)
  /* package */ abstract Serde<SensorStateDuration> avroDirectDuration(
      @Format(Name.AVRO_DIRECT) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_REFLECT)
  /* package */ abstract Serde<SensorState> confluentReflect(
      @Format(Name.CONFLUENT_REFLECT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_REFLECT)
  /* package */ abstract Serde<SensorStateDuration> confluentReflectDuration(
      @Format(Name.CONFLUENT_REFLECT) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_SPECIFIC)
  /* package */ abstract Serde<SensorState> confluentSpecific(
      @Format(Name.CONFLUENT_SPECIFIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_SPECIFIC)
  /* package */ abstract Serde<SensorStateDuration> confluentSpecificDuration(
      @Format(Name.CONFLUENT_SPECIFIC) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_DIRECT)
  /* package */ abstract Serde<SensorState> confluentDirect(
      @Format(Name.CONFLUENT_DIRECT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_DIRECT)
  /* package */ abstract Serde<SensorStateDuration> confluentDirectDuration(
      @Format(Name.CONFLUENT_DIRECT) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_GENERIC)
  /* package */ abstract Serde<SensorState> confluentGenerict(
      @Format(Name.CONFLUENT_GENERIC) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_GENERIC)
  /* package */ abstract Serde<SensorStateDuration> confluentGenerictDuration(
      @Format(Name.CONFLUENT_GENERIC) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_PROTO)
  /* package */ abstract Serde<SensorState> confluentProto(
      @Format(Name.CONFLUENT_PROTO) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_PROTO)
  /* package */ abstract Serde<SensorStateDuration> confluentProtoDuration(
      @Format(Name.CONFLUENT_PROTO) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_JSON)
  /* package */ abstract Serde<SensorState> confluentJson(
      @Format(Name.CONFLUENT_JSON) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.CONFLUENT_JSON)
  /* package */ abstract Serde<SensorStateDuration> confluentJsonDuration(
      @Format(Name.CONFLUENT_JSON) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.GSON)
  /* package */ abstract Serde<SensorState> gson(@Format(Name.GSON) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.GSON)
  /* package */ abstract Serde<SensorStateDuration> gsonDuration(
      @Format(Name.GSON) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.GSON_FAST)
  /* package */ abstract Serde<SensorState> gsonFast(
      @Format(Name.GSON_FAST) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.GSON_FAST)
  /* package */ abstract Serde<SensorStateDuration> gsonFastDuration(
      @Format(Name.GSON_FAST) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.JSON)
  /* package */ abstract Serde<SensorState> json(@Format(Name.JSON) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.JSON)
  /* package */ abstract Serde<SensorStateDuration> jsonDuration(
      @Format(Name.JSON) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.PROTO)
  /* package */ abstract Serde<SensorState> proto(@Format(Name.PROTO) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.PROTO)
  /* package */ abstract Serde<SensorStateDuration> protoDuration(
      @Format(Name.PROTO) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.ION_BINARY)
  /* package */ abstract Serde<SensorState> ionBinary(
      @Format(Name.ION_BINARY) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.ION_BINARY)
  /* package */ abstract Serde<SensorStateDuration> ionBinaryDuration(
      @Format(Name.ION_BINARY) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.ION_TEXT)
  /* package */ abstract Serde<SensorState> ionText(
      @Format(Name.ION_TEXT) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.ION_TEXT)
  /* package */ abstract Serde<SensorStateDuration> ionTextDuration(
      @Format(Name.ION_TEXT) Serde<SensorStateDuration> serde);

  @Binds
  @IntoMap
  @NameKey(Name.MIXIN)
  /* package */ abstract Serde<SensorState> mixin(@Format(Name.MIXIN) Serde<SensorState> serde);

  @Binds
  @IntoMap
  @NameKey(Name.MIXIN)
  /* package */ abstract Serde<SensorStateDuration> mixinDuration(
      @Format(Name.MIXIN) Serde<SensorStateDuration> serde);
}
