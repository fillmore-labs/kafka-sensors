package com.fillmore_labs.kafka.sensors.app;

import com.fillmore_labs.kafka.sensors.app.context.MainComponent;
import com.fillmore_labs.kafka.sensors.lifecycle.StartUpManager;
import io.helidon.config.Config;
import io.helidon.config.ConfigSources;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "kafka-sensors", mixinStandardHelpOptions = true)
public final class MainCommand implements Callable<Integer> {
  @Option(
      names = {"-c", "--config"},
      paramLabel = "CONFIGURATION",
      description = "the configuration file")
  public Path configFile = Paths.get("conf", "application.yaml");

  private MainCommand() {}

  public static Callable<Integer> command() {
    return new MainCommand();
  }

  private static StartUpManager createStartUpManager(Path configFile) {
    var config = readConfig(configFile);
    var main = createMainComponent(config);

    return main.startUpManager();
  }

  private static Config readConfig(Path configFile) {
    var configSource = ConfigSources.file(configFile).build();
    return Config.builder(configSource)
        .disableSystemPropertiesSource()
        .disableEnvironmentVariablesSource()
        .build();
  }

  private static MainComponent createMainComponent(Config config) {
    return MainComponent.factory().newMainComponent(config);
  }

  @Override
  public Integer call() {
    var startUpManager = createStartUpManager(configFile);
    startUpManager.startUp();
    return 0;
  }
}
