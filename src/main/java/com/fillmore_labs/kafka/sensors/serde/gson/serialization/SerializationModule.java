package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.gson.GsonDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.gson.GsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Module
public abstract class SerializationModule {
  private SerializationModule() {}

  @Provides
  @Singleton
  /* package */ static Gson gson() {
    return new GsonBuilder().registerTypeAdapterFactory(new GsonAdaptersSerialization()).create();
  }

  @Provides
  /* package */ static Serializer<SensorStateGson> sensorStateSerializer(Gson gson) {
    var adapter = gson.getAdapter(SensorStateGson.class);
    return new GsonSerializer<>(adapter);
  }

  @Provides
  /* package */ static Deserializer<SensorStateGson> sensorStateDeserializer(Gson gson) {
    var adapter = gson.getAdapter(SensorStateGson.class);
    return new GsonDeserializer<>(adapter);
  }

  @Provides
  /* package */ static Serializer<SensorStateDurationGson> sensorStateDurationSerializer(
      Gson gson) {
    var adapter = gson.getAdapter(SensorStateDurationGson.class);
    return new GsonSerializer<>(adapter);
  }

  @Provides
  /* package */ static Deserializer<SensorStateDurationGson> sensorStateDurationDeserializer(
      Gson gson) {
    var adapter = gson.getAdapter(SensorStateDurationGson.class);
    return new GsonDeserializer<>(adapter);
  }
}
