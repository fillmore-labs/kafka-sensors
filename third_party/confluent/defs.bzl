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
        sha256 = "5c67949249d151c6d592d3904cd815bb94e4e2ebe89bc6e9da9a3206f8e863fd",
        strip_prefix = "common-7.2.0-895",
        urls = ["https://github.com/confluentinc/common/archive/refs/tags/v7.2.0-895.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "7f05cd163db4887780013e9245d2aa942a8b56810dbbf8004e0e175294ff8e34",
        strip_prefix = "schema-registry-7.2.0-942",
        urls = ["https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.2.0-942.tar.gz"],
    )
