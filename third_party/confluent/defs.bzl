""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.4",
    "com.google.api.grpc:proto-google-common-protos:2.30.0",
    "com.squareup.wire:wire-runtime-jvm:4.9.3",
    "com.squareup.wire:wire-schema-jvm:4.9.3",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.20",
    "org.apache.commons:commons-compress:1.25.0",
    "org.apache.commons:commons-lang3:3.14.0",
    "org.jetbrains.kotlin:kotlin-stdlib:1.9.22",
    "org.json:json:20231013",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "9c0d5657ae571d0b0eb71f02c1d8075c0c13c79834603376030d69d1755a86cf",
        strip_prefix = "common-7.5.3",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.5.3.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        patches = ["//third_party/confluent:schema_registry.patch"],
        sha256 = "c8cdc82439efa44256bd5c017028d731632215b92e9f72e390e548e2af273785",
        strip_prefix = "schema-registry-7.5.3",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.5.3.tar.gz",
    )
