package com.fillmore_labs.kafka.sensors.app.context;

import com.fillmore_labs.kafka.sensors.configuration.App;
import com.fillmore_labs.kafka.sensors.configuration.KafkaConfiguration;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.SensorStateDuration;
import com.fillmore_labs.kafka.sensors.serde.common.Format;
import com.fillmore_labs.kafka.sensors.serde.common.Name;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.serde.json.JsonModule;
import com.fillmore_labs.kafka.sensors.serde.proto.ProtoModule;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import org.apache.kafka.common.serialization.Serde;

@Module(
    includes = {
      JsonModule.class,
      ProtoModule.class,
    })
public abstract class AppSerDesModule {
  private AppSerDesModule() {}

  @Binds
  @App.InputSerde
  /* package */ abstract Serde<SensorState> inputSerde(@Format(Name.JSON) Serde<SensorState> serde);

  @Binds
  @App.ResultSerde
  /* package */ abstract Serde<SensorStateDuration> resultSerde(
      @Format(Name.JSON) Serde<SensorStateDuration> serde);

  @Binds
  @App.StoreSerde
  /* package */ abstract Serde<SensorState> storeSerde(
      @Format(Name.PROTO) Serde<SensorState> serde);

  @Provides
  @SchemaRegistryUrl
  /* package */ static String schemaRegistryUrl(KafkaConfiguration configuration) {
    return String.join(",", configuration.schemaRegistry());
  }
}
