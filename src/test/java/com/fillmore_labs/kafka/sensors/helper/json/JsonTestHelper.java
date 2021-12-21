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
  private static final String TESTDATA = "testdata";
  private static final Path OUTPUT_SCHEMA = Path.of(TESTDATA, "output-schema.json");
  private static final Path OUTPUT_SCHEMA_ISO = Path.of(TESTDATA, "output-schema-iso.json");
  private static final Path INPUT_SAMPLES = Path.of(TESTDATA, "input-samples.jsonl");
  private static final Path INPUT_SAMPLES_ISO = Path.of(TESTDATA, "input-samples-iso.jsonl");

  private JsonTestHelper() {}

  private static JsonSchema readSchema(Path path, ObjectMapper mapper) throws IOException {
    JsonNode node;
    try (var stream = Files.newInputStream(path)) {
      node = mapper.readTree(stream);
    }

    var versionFlag = SpecVersionDetector.detect(node);
    var factory = JsonSchemaFactory.getInstance(versionFlag);

    return factory.getSchema(node);
  }

  private static Set<ValidationMessage> validate(byte[] encoded, Path path) throws IOException {
    var mapper = new ObjectMapper();
    var schema = readSchema(path, mapper);

    var node = mapper.readTree(encoded);
    return schema.validate(node);
  }

  public static Set<ValidationMessage> validate(byte[] encoded) throws IOException {
    return validate(encoded, OUTPUT_SCHEMA);
  }

  public static Set<ValidationMessage> validateIso(byte[] encoded) throws IOException {
    return validate(encoded, OUTPUT_SCHEMA_ISO);
  }

  private static List<String> sampleInput(Path path) {
    try {
      return Files.readAllLines(path);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static List<String> sampleInput() {
    return sampleInput(INPUT_SAMPLES);
  }

  public static List<String> sampleInputIso() {
    return sampleInput(INPUT_SAMPLES_ISO);
  }
}
