package com.fillmore_labs.kafka.sensors.serde.confluent.interop;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.message.SchemaStore.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public final class ExceptionTest {
  @Mock
  @SuppressWarnings("nullness:initialization.field.uninitialized")
  public Cache resolver;

  @Mock
  @SuppressWarnings("nullness:initialization.field.uninitialized")
  public SchemaRegistryClient registryClient;

  @Mock
  @SuppressWarnings("nullness:initialization.field.uninitialized")
  public ParsedSchema parsedSchema;

  @Test
  @SuppressWarnings("nullness:argument") // assertThat annotation is commented
  public void testNullA2C() {
    var recoder = new Avro2Confluent(resolver, registryClient);
    var encoded = new byte[0];
    assertThat(recoder.transform(TestHelper.KAFKA_TOPIC, encoded)).isNull();
  }

  @Test
  public void testTooShortA2C() {
    var recoder = new Avro2Confluent(resolver, registryClient);
    var encoded = new byte[] {(byte) 0xc3, (byte) 0x01, 0, 0, 0, 0, 0, 0, 0};
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> recoder.transform(TestHelper.KAFKA_TOPIC, encoded));
    assertThat(exception).hasMessageThat().contains("encoding");
    assertThat(exception).hasCauseThat().isNull();
  }

  @Test
  public void testMagicA2C() {
    var recoder = new Avro2Confluent(resolver, registryClient);
    var encoded = new byte[] {(byte) 0xc3, (byte) 0x02, 0, 0, 0, 0, 0, 0, 0, 0};
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> recoder.transform(TestHelper.KAFKA_TOPIC, encoded));
    assertThat(exception).hasMessageThat().contains("magic");
    assertThat(exception).hasCauseThat().isNull();
  }

  @Test
  public void testRegistryClientA2C() throws RestClientException, IOException {
    given(resolver.findByFingerprint(0L)).willReturn(Schema.create(Schema.Type.NULL));
    given(registryClient.register(anyString(), any(ParsedSchema.class)))
        .willThrow(new RestClientException("", 0, 0));
    var recoder = new Avro2Confluent(resolver, registryClient);

    var encoded = new byte[] {(byte) 0xc3, (byte) 0x01, 0, 0, 0, 0, 0, 0, 0, 0};
    var exception =
        assertThrows(
            IllegalStateException.class, () -> recoder.transform(TestHelper.KAFKA_TOPIC, encoded));
    assertThat(exception).hasCauseThat().isInstanceOf(RestClientException.class);
  }

  @Test
  @SuppressWarnings("nullness:argument") // willReturn is not annotated
  public void testMissingA2C() {
    given(resolver.findByFingerprint(0L)).willReturn(null);
    var recoder = new Avro2Confluent(resolver, registryClient);

    var encoded = new byte[] {(byte) 0xc3, (byte) 0x01, 0, 0, 0, 0, 0, 0, 0, 0};
    var exception =
        assertThrows(
            IllegalStateException.class, () -> recoder.transform(TestHelper.KAFKA_TOPIC, encoded));
    assertThat(exception).hasCauseThat().isNull();
  }

  @Test
  @SuppressWarnings("nullness:argument") // assertThat annotation is commented
  public void testNullC2A() {
    var recoder = new Confluent2Avro(resolver, registryClient);
    var encoded = new byte[0];
    assertThat(recoder.transform(TestHelper.KAFKA_TOPIC, encoded)).isNull();
  }

  @Test
  public void testTooShortC2A() {
    var recoder = new Confluent2Avro(resolver, registryClient);
    var encoded = new byte[] {0, 0, 0, 0};
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> recoder.transform(TestHelper.KAFKA_TOPIC, encoded));
    assertThat(exception).hasMessageThat().contains("encoding");
    assertThat(exception).hasCauseThat().isNull();
  }

  @Test
  public void testMagicC2A() {
    var recoder = new Confluent2Avro(resolver, registryClient);
    var encoded = new byte[] {1, 0, 0, 0, 0};
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> recoder.transform(TestHelper.KAFKA_TOPIC, encoded));
    assertThat(exception).hasMessageThat().contains("magic");
    assertThat(exception).hasCauseThat().isNull();
  }

  @Test
  public void testRegistryClientC2A() throws RestClientException, IOException {
    given(registryClient.getSchemaById(anyInt())).willThrow(new RestClientException("", 0, 0));
    var recoder = new Confluent2Avro(resolver, registryClient);

    var encoded = new byte[] {0, 0, 0, 0, 0};
    var exception =
        assertThrows(
            IllegalStateException.class, () -> recoder.transform(TestHelper.KAFKA_TOPIC, encoded));
    assertThat(exception).hasCauseThat().isInstanceOf(RestClientException.class);
  }

  @Test
  public void testSchemaTypeC2A() throws RestClientException, IOException {
    given(parsedSchema.rawSchema()).willReturn(new Object());
    given(registryClient.getSchemaById(0)).willReturn(parsedSchema);
    var recoder = new Confluent2Avro(resolver, registryClient);

    var encoded = new byte[] {0, 0, 0, 0, 0};
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> recoder.transform(TestHelper.KAFKA_TOPIC, encoded));
    assertThat(exception).hasCauseThat().isNull();
  }
}
