package com.fillmore_labs.kafka.sensors.serde.gson_faster.serialization;

import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateDurationGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.serializer.gson_faster.GsonFastDeserializer;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import org.apache.kafka.common.serialization.Deserializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Named("faster")
  /* package */ static Deserializer<SensorStateGson> sensorStateFastDeserializer(Gson gson) {
    var adapter = gson.getAdapter(SensorStateGson.class);
    return new GsonFastDeserializer<>(adapter);
  }

  @Provides
  @Named("faster")
  /* package */ static Deserializer<SensorStateDurationGson> sensorStateDurationFastDeserializer(
      Gson gson) {
    var adapter = gson.getAdapter(SensorStateDurationGson.class);
    return new GsonFastDeserializer<>(adapter);
  }
}
