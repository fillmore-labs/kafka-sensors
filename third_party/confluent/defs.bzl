""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.8.3",
    "com.kjetland:mbknor-jackson-jsonschema_2.13:1.0.39",
    "com.squareup.wire:wire-runtime-jvm:4.2.0",
    "com.squareup.wire:wire-schema-jvm:4.2.0",
    "io.swagger.core.v3:swagger-annotations:2.2.0",
    "jakarta.servlet:jakarta.servlet-api:4.0.4",
    "jakarta.validation:jakarta.validation-api:2.0.2",
    "jakarta.ws.rs:jakarta.ws.rs-api:2.1.6",
    "org.jetbrains.kotlin:kotlin-stdlib:1.5.32",
    "org.json:json:20220320",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "8959fc4a4dd79946a4f8253d25025d7969c7142f7593e1741a37b99cbd12609c",
        strip_prefix = "common-7.1.0",
        urls = ["https://github.com/confluentinc/common/archive/refs/tags/v7.1.0.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "18e9d4e35573cb130a92fa1725d0cac78dc2c325358cd095595dd3c3c5aa3c14",
        strip_prefix = "schema-registry-7.1.0",
        urls = ["https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.1.0.tar.gz"],
    )
