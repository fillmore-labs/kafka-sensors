package com.fillmore_labs.kafka.sensors.app.context;

import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import dagger.Module;
import dagger.Provides;
import jakarta.inject.Singleton;
import java.util.Set;

/** Bindings for our {@link Service}s. */
@Module
/* package */ abstract class StartUpModule {
  private StartUpModule() {}

  @Provides
  @Singleton
  /* package */ static ServiceManager serviceManager(Set<Service> services) {
    return new ServiceManager(services);
  }
}
