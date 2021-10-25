package com.fillmore_labs.kafka.sensors.helper.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public final class JsonTestHelper {
  private static final Path OUTPUT_SCHEMA = Path.of("testdata", "output-schema.json");
  private static final Path INPUT_SAMPLES = Path.of("testdata", "input-samples.jsonl");

  private JsonTestHelper() {}

  private static JsonSchema readSchema(ObjectMapper mapper) throws IOException {
    JsonNode node;
    try (var stream = Files.newInputStream(OUTPUT_SCHEMA)) {
      node = mapper.readTree(stream);
    }

    var versionFlag = SpecVersionDetector.detect(node);
    var factory = JsonSchemaFactory.getInstance(versionFlag);

    return factory.getSchema(node);
  }

  public static Set<ValidationMessage> validate(byte[] encoded) throws IOException {
    var mapper = new ObjectMapper();
    var schema = readSchema(mapper);

    var node = mapper.readTree(encoded);
    return schema.validate(node);
  }

  public static List<String> sampleInput() {
    try {
      return Files.readAllLines(INPUT_SAMPLES);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
