""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.2",
    "com.google.api.grpc:proto-google-common-protos:2.21.0",
    "com.squareup.wire:wire-runtime-jvm:4.7.2",
    "com.squareup.wire:wire-schema-jvm:4.7.2",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.13",
    "org.apache.commons:commons-compress:1.23.0",
    "org.apache.commons:commons-lang3:3.12.0",
    "org.jetbrains.kotlin:kotlin-stdlib:1.8.22",
    "org.json:json:20230618",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "70ac3da68adc00f38b78005da32a304705b63d6d29c1b0a8dd306f251a0b81c5",
        strip_prefix = "common-7.4.0",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.4.0.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "b65fd1fe32a8c70fc977f43cd1bb00f73a48fad59c147c62ce00f1ad550d0d10",
        strip_prefix = "schema-registry-7.4.0",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.4.0.tar.gz",
    )
