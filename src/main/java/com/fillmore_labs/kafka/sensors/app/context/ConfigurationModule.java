package com.fillmore_labs.kafka.sensors.app.context;

import com.fillmore_labs.kafka.sensors.configuration.KafkaConfiguration;
import dagger.Module;
import dagger.Provides;
import io.helidon.config.Config;
import javax.inject.Singleton;

/** Provides the {@link KafkaConfiguration} for our main app. */
@Module
public abstract class ConfigurationModule {
  private ConfigurationModule() {}

  @Provides
  @Singleton
  /* package */ static KafkaConfiguration kafkaConfiguration(Config config) {
    var subConfig = config.get(KafkaConfiguration.PREFIX);
    return subConfig.as(KafkaConfiguration.class).get();
  }
}
