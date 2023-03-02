""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.14.1",
    "com.squareup.wire:wire-runtime-jvm:4.5.1",
    "com.squareup.wire:wire-schema-jvm:4.5.1",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.8",
    "org.apache.commons:commons-compress:1.22",
    "org.apache.commons:commons-lang3:3.12.0",
    "org.jetbrains.kotlin:kotlin-stdlib:1.8.10",
    "org.json:json:20220924",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "ad8ef31abad9e0f7e1faf9966449f5bad92d62c07c8709e03a1714691b97731c",
        strip_prefix = "common-7.4.0-rc230301201210",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.4.0-rc230301201210.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "60afde65713a5537a9717b60a7909c8d45a97fe7e77957fcab4169dcb655f500",
        strip_prefix = "schema-registry-7.4.0-rc230301201210",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.4.0-rc230301201210.tar.gz",
    )
