package com.fillmore_labs.kafka.sensors.serde.gson_faster.serialization;

import com.fillmore_labs.kafka.sensors.serde.gson.serialization.ReadingGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.SensorStateGson;
import com.fillmore_labs.kafka.sensors.serde.gson.serialization.StateDurationGson;
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
  /* package */ static Deserializer<ReadingGson> readingFastDeserializer(Gson gson) {
    var adapter = gson.getAdapter(ReadingGson.class);
    return new GsonFastDeserializer<>(adapter);
  }

  @Provides
  @Named("faster")
  /* package */ static Deserializer<SensorStateGson> sensorStateFastDeserializer(Gson gson) {
    var adapter = gson.getAdapter(SensorStateGson.class);
    return new GsonFastDeserializer<>(adapter);
  }

  @Provides
  @Named("faster")
  /* package */ static Deserializer<StateDurationGson> stateDurationFastDeserializer(Gson gson) {
    var adapter = gson.getAdapter(StateDurationGson.class);
    return new GsonFastDeserializer<>(adapter);
  }
}
