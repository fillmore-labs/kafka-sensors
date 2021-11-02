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
  /* package */ static Serializer<ReadingGson> readingSerializer(Gson gson) {
    var adapter = gson.getAdapter(ReadingGson.class);
    return new GsonSerializer<>(adapter);
  }

  @Provides
  /* package */ static Deserializer<ReadingGson> readingDeserializer(Gson gson) {
    var adapter = gson.getAdapter(ReadingGson.class);
    return new GsonDeserializer<>(adapter);
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
  /* package */ static Serializer<StateDurationGson> stateDurationSerializer(Gson gson) {
    var adapter = gson.getAdapter(StateDurationGson.class);
    return new GsonSerializer<>(adapter);
  }

  @Provides
  /* package */ static Deserializer<StateDurationGson> stateDurationDeserializer(Gson gson) {
    var adapter = gson.getAdapter(StateDurationGson.class);
    return new GsonDeserializer<>(adapter);
  }
}
