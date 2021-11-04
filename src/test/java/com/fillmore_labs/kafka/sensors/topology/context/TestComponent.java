package com.fillmore_labs.kafka.sensors.topology.context;

import com.fillmore_labs.kafka.sensors.serde.all_serdes.AllSerdesModule;
import com.fillmore_labs.kafka.sensors.serde.confluent.common.SchemaRegistryModule;
import com.fillmore_labs.kafka.sensors.topology.server.EmbeddedKafka;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import java.util.Properties;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

@Singleton
@Component(modules = {AllSerdesModule.class, TestComponent.TestModule.class})
public interface TestComponent {
  static TestComponent.Builder builder() {
    return DaggerTestComponent.builder();
  }

  Parameters parameters();

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder embeddedKafka(EmbeddedKafka embeddedKafka);

    TestComponent build();
  }

  @Module(includes = SchemaRegistryModule.class, subcomponents = SingleTestComponent.class)
  /* package */ abstract class TestModule {

    private static final String APPLICATION_ID = "topology-test";
    private static final String PARTITIONS = "1";

    private TestModule() {}

    @Provides
    @Singleton
    /* package */ static Properties configuration(EmbeddedKafka kafkaTestResource) {
      var settings = new Properties();
      settings.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
      settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaTestResource.getBrokerList());
      settings.put(
          StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
      settings.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, PARTITIONS);

      return settings;
    }
  }
}
