package com.fillmore_labs.kafka.sensors.streams;

import com.fillmore_labs.kafka.sensors.configuration.KafkaConfiguration;
import com.google.common.util.concurrent.AbstractIdleService;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;

/** Configures, starts and shuts down our {@link KafkaStreams}. */
@Singleton
/* package */ final class KafkaStreamsService extends AbstractIdleService {
  private static final String DEFAULT_CLIENT_ID = "default";

  /** See {@link org.apache.kafka.common.serialization.StringSerializer}. */
  private static final String KEY_SERIALIZER_ENCODING = "key.serializer.encoding";
  /** See {@link org.apache.kafka.common.serialization.StringDeserializer}. */
  private static final String KEY_DESERIALIZER_ENCODING = "key.deserializer.encoding";

  private final KafkaStreams kafkaStreams;

  @Inject
  /* package */ KafkaStreamsService(Topology topology, KafkaConfiguration configuration) {
    super();
    var settings = new Properties();
    putIntoSettings(configuration, settings);

    // https://kafka.apache.org/documentation/streams/developer-guide/config-streams.html#default-deserialization-exception-handler
    settings.put(
        StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
        LogAndContinueExceptionHandler.class);
    // https://kafka.apache.org/documentation/streams/developer-guide/config-streams.html#default-production-exception-handler
    settings.put(
        StreamsConfig.DEFAULT_PRODUCTION_EXCEPTION_HANDLER_CLASS_CONFIG,
        ContinueProductionExceptionHandler.class);

    kafkaStreams = new KafkaStreams(topology, settings);
  }

  private static void putIntoSettings(KafkaConfiguration configuration, Properties settings) {
    var clientID = configuration.clientID().orElse(DEFAULT_CLIENT_ID);
    var brokers = String.join(",", configuration.bootstrapServers());

    var encoding = StandardCharsets.US_ASCII.name();
    settings.put(StreamsConfig.APPLICATION_ID_CONFIG, clientID);
    settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
    settings.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
    settings.put(KEY_SERIALIZER_ENCODING, encoding);
    settings.put(KEY_DESERIALIZER_ENCODING, encoding);
  }

  @Override
  protected void startUp() {
    kafkaStreams.start();
  }

  @Override
  protected void shutDown() {
    kafkaStreams.close();
  }
}
