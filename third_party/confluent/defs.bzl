""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.11.0",
    "com.squareup.wire:wire-runtime-jvm:4.4.3",
    "com.squareup.wire:wire-schema-jvm:4.4.3",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.7",
    "org.apache.commons:commons-lang3:3.12.0",
    "org.jetbrains.kotlin:kotlin-stdlib:1.7.21",
    "org.json:json:20220924",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "bfce29f6abf5d14a794f26388ae038c30e4b580e932e2d31a6ebfca89c15aac5",
        strip_prefix = "common-7.3.1",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.1.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "1549f5b3650eccd6ba0a96760c289315a077b86e9489d1ac2f6373867f87c5b6",
        strip_prefix = "schema-registry-7.3.1-rc221212170605",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.1-rc221212170605.tar.gz",
    )
