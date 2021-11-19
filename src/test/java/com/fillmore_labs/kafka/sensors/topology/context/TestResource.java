package com.fillmore_labs.kafka.sensors.topology.context;

import com.fillmore_labs.kafka.sensors.topology.server.EmbeddedKafka;
import java.util.Properties;
import org.apache.kafka.streams.StreamsConfig;
import org.junit.rules.ExternalResource;

public final class TestResource extends ExternalResource {
  private static final String APPLICATION_ID = "topology-test";
  private static final String PARTITIONS = "1";

  private final EmbeddedKafka embeddedKafka;
  private final TestComponent testComponent;

  public TestResource() {
    super();
    this.embeddedKafka = new EmbeddedKafka(1);

    var configuration = configuration(embeddedKafka);
    this.testComponent = TestComponent.builder().configuration(configuration).build();
  }

  private static Properties configuration(EmbeddedKafka embeddedKafka) {
    var settings = new Properties();
    settings.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
    settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafka.getBrokerList());
    settings.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, PARTITIONS);

    return settings;
  }

  public SingleTestComponent.Builder singleTestComponentBuilder() {
    return testComponent.singleTestComponentBuilder();
  }

  // called before `before`
  public Iterable<Formats> parameters() {
    return testComponent.parameters();
  }

  @Override
  protected void before() {
    embeddedKafka.startup();
  }

  @Override
  protected void after() {
    embeddedKafka.shutdown();
  }
}
