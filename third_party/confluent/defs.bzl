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
        sha256 = "29b2fb622dc58c59856b97e08b1ecc6e38c4c16950c463b481b99e63c40a49f1",
        strip_prefix = "common-7.3.0-rc220831005356",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.0-rc220831005356.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "446ebaea7bead73b98a5218c59dc49ff9fc9e92c90f686a36971982751f5c6c4",
        strip_prefix = "schema-registry-7.3.0-rc220831005356",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.0-rc220831005356.tar.gz",
    )
