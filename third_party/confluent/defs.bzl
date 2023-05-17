""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.2",
    "com.google.api.grpc:proto-google-common-protos:2.18.0",
    "com.squareup.wire:wire-runtime-jvm:4.6.2",
    "com.squareup.wire:wire-schema-jvm:4.6.2",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.10",
    "org.apache.commons:commons-compress:1.23.0",
    "org.apache.commons:commons-lang3:3.12.0",
    "org.jetbrains.kotlin:kotlin-stdlib:1.8.21",
    "org.json:json:20230227",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "c706cc4982fcdbc9d4cb8bde4773d03503d9937c7e8729e5be5b6f581e4f5533",
        strip_prefix = "common-7.4.0-rc230501101036",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.4.0-rc230501101036.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "5b4736805b8b9c137d3c13d5d6250f39b9c1fbbff23d9b4275c6d24b4e656b3a",
        strip_prefix = "schema-registry-7.4.0-rc230501101036",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.4.0-rc230501101036.tar.gz",
    )
