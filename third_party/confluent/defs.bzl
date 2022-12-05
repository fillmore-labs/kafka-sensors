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
        sha256 = "58d8c9f242d9a0ed9286cc80e794987b009210fa715f4bc57a6c3746054ef846",
        strip_prefix = "common-7.3.1-rc221205184414",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.1-rc221205184414.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "e4ea0bd40f722c0c693d33de758f750d2ee77dd99b3da63829b360a11a7749ae",
        strip_prefix = "schema-registry-7.3.1-rc221205184414",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.1-rc221205184414.tar.gz",
    )
