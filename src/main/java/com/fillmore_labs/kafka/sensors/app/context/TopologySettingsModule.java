package com.fillmore_labs.kafka.sensors.app.context;

import static com.fillmore_labs.kafka.sensors.serde.json.JsonModule.JSON;
import static com.fillmore_labs.kafka.sensors.serde.proto.ProtoModule.PROTO;

import com.fillmore_labs.kafka.sensors.configuration.KafkaConfiguration;
import com.fillmore_labs.kafka.sensors.model.Reading;
import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryUrl;
import com.fillmore_labs.kafka.sensors.serde.json.JsonModule;
import com.fillmore_labs.kafka.sensors.serde.proto.ProtoModule;
import com.fillmore_labs.kafka.sensors.topology.TopologySettings;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Serde;

@Module(
    includes = {
      JsonModule.class,
      ProtoModule.class,
    })
public abstract class TopologySettingsModule {
  private TopologySettingsModule() {}

  @Provides
  /* package */ static TopologySettings topologySettings(
      KafkaConfiguration configuration,
      @Named(JSON) Serde<SensorState> inputSerde,
      @Named(PROTO) Serde<Reading> storeSerde,
      @Named(JSON) Serde<StateDuration> resultSerde) {
    return TopologySettings.builder()
        .inputSerde(inputSerde)
        .inputTopic(configuration.inputTopic())
        .storeSerde(storeSerde)
        .storeName("DURATION-STORE")
        .resultSerde(resultSerde)
        .resultTopic(configuration.resultTopic())
        .build();
  }

  @Provides
  @SchemaRegistryUrl
  /* package */ static String schemaRegistryUrl(KafkaConfiguration configuration) {
    return String.join(",", configuration.schemaRegistry());
  }
}
