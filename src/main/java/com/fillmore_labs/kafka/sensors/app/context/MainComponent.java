package com.fillmore_labs.kafka.sensors.app.context;

import com.fillmore_labs.kafka.sensors.lifecycle.StartUpManager;
import dagger.BindsInstance;
import dagger.Component;
import io.helidon.config.Config;
import javax.inject.Singleton;

@Singleton
@Component(modules = {MainModule.class})
public abstract class MainComponent {
  /* package */ MainComponent() {}

  public static Factory factory() {
    return DaggerMainComponent.factory();
  }

  public abstract StartUpManager startUpManager();

  @Component.Factory
  public abstract static class Factory {
    public abstract MainComponent newMainComponent(@BindsInstance Config config);
  }
}
