package com.fillmore_labs.kafka.sensors.streams;

import com.google.common.util.concurrent.Service;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/** Binds {@link KafkaStreamsService} to a {@link Service}. */
@Module
public abstract class StreamsModule {
  private StreamsModule() {}

  @Binds
  @IntoSet
  /* package */ abstract Service streams(KafkaStreamsService service);
}
