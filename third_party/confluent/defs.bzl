""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.9.4",
    "com.squareup.wire:wire-runtime-jvm:4.4.1",
    "com.squareup.wire:wire-schema-jvm:4.4.1",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.3",
    "org.apache.commons:commons-lang3:3.12.0",
    "org.jetbrains.kotlin:kotlin-stdlib:1.7.20",
    "org.json:json:20220924",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "2476164ffe3cee8fd09e2a14cb3a5d798f54902fc04e07c6f34fe5ce14a61f9e",
        strip_prefix = "common-7.3.0-789",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.0-789.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "5f5b52d1f7001afb787ec111dbdfc45e957f82f17c7866af2b9048e922bc2680",
        strip_prefix = "schema-registry-7.3.0-839",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.0-839.tar.gz",
    )
