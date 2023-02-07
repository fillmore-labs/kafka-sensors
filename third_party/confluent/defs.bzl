""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.14.0",
    "com.squareup.wire:wire-runtime-jvm:4.5.0",
    "com.squareup.wire:wire-schema-jvm:4.5.0",
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
        sha256 = "60e71e221b0d148d24ec4c37e55f671ac3354872fb4fcabe02f4dc948a4da762",
        strip_prefix = "common-7.4.0-971",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.4.0-971.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "2d59a0c30e543b4b1691e0b858bc05f138290b3ff45768db6611507cb85544b2",
        strip_prefix = "schema-registry-7.4.0-901",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.4.0-901.tar.gz",
    )
