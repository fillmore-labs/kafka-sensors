""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.13.0",
    "com.squareup.wire:wire-runtime-jvm:4.4.3",
    "com.squareup.wire:wire-schema-jvm:4.4.3",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.8",
    "org.apache.commons:commons-lang3:3.12.0",
    "org.jetbrains.kotlin:kotlin-stdlib:1.8.0",
    "org.json:json:20220924",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "f97f87c5677b79c77df8eb10c4940dc6210f44fc2f126843e9d63918116173e1",
        strip_prefix = "common-7.3.1",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.1.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "6986a71ee3807ba1f5079d11c24ad5460d5f2e1e50f01dc2eb737ed21ab4f515",
        strip_prefix = "schema-registry-7.3.1",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.1.tar.gz",
    )
