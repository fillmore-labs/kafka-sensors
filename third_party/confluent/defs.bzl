""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.9.1",
    "com.kjetland:mbknor-jackson-jsonschema_2.13:1.0.39",
    "com.squareup.wire:wire-runtime-jvm:4.4.0",
    "com.squareup.wire:wire-schema-jvm:4.4.0",
    "io.swagger.core.v3:swagger-annotations:2.2.1",
    "jakarta.servlet:jakarta.servlet-api:4.0.4",
    "jakarta.validation:jakarta.validation-api:2.0.2",
    "jakarta.ws.rs:jakarta.ws.rs-api:2.1.6",
    "org.jetbrains.kotlin:kotlin-stdlib:1.6.10",
    "org.json:json:20220320",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "8b5f439d9bc6171ee721abc3952f261291cf7f9881101f260b174dff25bf56db",
        strip_prefix = "common-7.2.0",
        urls = ["https://github.com/confluentinc/common/archive/refs/tags/v7.2.0.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "4e679a784f9db70f90c8f13a894acb72c4856666cb1395aeba1b4366113a1849",
        strip_prefix = "schema-registry-7.2.0",
        urls = ["https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.2.0.tar.gz"],
    )
