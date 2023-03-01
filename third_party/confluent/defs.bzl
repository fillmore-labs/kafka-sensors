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
        sha256 = "e0ab644159e412f2ab95ace4eb04945a70da8cd2017f7e1fe5b10187347b3701",
        strip_prefix = "common-7.4.0-rc230301003002",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.4.0-rc230301003002.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "608f690988273fe3d04f18f3fac655c20d5579e96b72bfd1f9e957521c839c92",
        strip_prefix = "schema-registry-7.4.0-rc230301003002",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.4.0-rc230301003002.tar.gz",
    )
