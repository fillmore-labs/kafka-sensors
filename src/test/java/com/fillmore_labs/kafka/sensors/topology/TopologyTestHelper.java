package com.fillmore_labs.kafka.sensors.topology;

import com.fillmore_labs.kafka.sensors.configuration.KafkaConfiguration;
import com.fillmore_labs.kafka.sensors.topology.server.EmbeddedKafka;
import com.google.common.base.Splitter;
import java.util.Properties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

/* package */ final class TopologyTestHelper {
  /* package */ static final String INPUT_TOPIC = "input-topic";
  /* package */ static final String RESULT_TOPIC = "result-topic";
  private static final String APPLICATION_ID = "topology-test";
  private static final String PARTITIONS = "1";

  private TopologyTestHelper() {}

  /* package */ static EmbeddedKafka newKafkaTestResource() {
    return new EmbeddedKafka();
  }

  /* package */ static Properties settings(EmbeddedKafka kafkaTestResource) {
    var settings = new Properties();
    settings.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
    settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaTestResource.getBrokerList());
    settings.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
    settings.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, PARTITIONS);

    return settings;
  }

  /* package */ static KafkaConfiguration configuration(EmbeddedKafka kafkaTestResource) {
    return KafkaConfiguration.builder()
        .inputTopic(INPUT_TOPIC)
        .resultTopic(RESULT_TOPIC)
        .bootstrapServers(bootstrapServers(kafkaTestResource))
        .build();
  }

  private static Iterable<String> bootstrapServers(EmbeddedKafka kafkaTestResource) {
    return Splitter.on(',').split(kafkaTestResource.getBrokerList());
  }
}
