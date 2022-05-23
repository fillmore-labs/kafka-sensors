""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.8.3",
    "com.kjetland:mbknor-jackson-jsonschema_2.13:1.0.39",
    "com.squareup.wire:wire-runtime-jvm:4.3.0",
    "com.squareup.wire:wire-schema-jvm:4.3.0",
    "io.swagger.core.v3:swagger-annotations:2.2.0",
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
        sha256 = "23f414217a8eae189b1a20511ebb7c098ec94aa4aa706b19a8575bd7c8ec581e",
        strip_prefix = "common-7.3.0-320",
        urls = ["https://github.com/confluentinc/common/archive/refs/tags/v7.3.0-320.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "a6d20e2fedab03898fe49e83310d0468fb7e409baacbd78ed3d2f94966bc69fc",
        strip_prefix = "schema-registry-7.3.0-331",
        urls = ["https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.0-331.tar.gz"],
    )
