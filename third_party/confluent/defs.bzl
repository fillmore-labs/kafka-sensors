""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.14.2",
    "com.squareup.wire:wire-runtime-jvm:4.5.2",
    "com.squareup.wire:wire-schema-jvm:4.5.2",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.8",
    "org.apache.commons:commons-compress:1.22",
    "org.apache.commons:commons-lang3:3.12.0",
    "org.jetbrains.kotlin:kotlin-stdlib:1.8.10",
    "org.json:json:20230227",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "72303c73e28a2ecf2a9b3c1bb1af0ff2713f40e64fb6d96eaeedbb09f155c8f4",
        strip_prefix = "common-7.4.0-rc230307174144",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.4.0-rc230307174144.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "b5d9b273864685876df669ddf7d9dce500b19d6dc817e0c9aff74471e5f993e9",
        strip_prefix = "schema-registry-7.4.0-rc230303183118",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.4.0-rc230303183118.tar.gz",
    )
