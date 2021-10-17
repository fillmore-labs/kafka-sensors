package com.fillmore_labs.kafka.sensors.serde.gson.serialization;

import com.fillmore_labs.kafka.sensors.serde.serializer.gson.GsonDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.gson.GsonFastDeserializer;
import com.fillmore_labs.kafka.sensors.serde.serializer.gson.GsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
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
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateGson> sensorStateSerializer(Gson gson) {
    var adapter = gson.getAdapter(SensorStateGson.class);
    return new GsonSerializer<>(adapter);
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateGson> sensorStateDeserializer(Gson gson) {
    var adapter = gson.getAdapter(SensorStateGson.class);
    return new GsonDeserializer<>(adapter);
  }

  @Provides
  @Named("fast")
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateGson> sensorStateFastDeserializer(Gson gson) {
    var adapter = gson.getAdapter(SensorStateGson.class);
    return new GsonFastDeserializer<>(adapter);
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Serializer<SensorStateDurationGson> sensorStateDurationSerializer(
      Gson gson) {
    var adapter = gson.getAdapter(SensorStateDurationGson.class);
    return new GsonSerializer<>(adapter);
  }

  @Provides
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateDurationGson> sensorStateDurationDeserializer(
      Gson gson) {
    var adapter = gson.getAdapter(SensorStateDurationGson.class);
    return new GsonDeserializer<>(adapter);
  }

  @Provides
  @Named("fast")
  @SuppressWarnings("CloseableProvides")
  /* package */ static Deserializer<SensorStateDurationGson> sensorStateDurationFastDeserializer(
      Gson gson) {
    var adapter = gson.getAdapter(SensorStateDurationGson.class);
    return new GsonFastDeserializer<>(adapter);
  }
}
