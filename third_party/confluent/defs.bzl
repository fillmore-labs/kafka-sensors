""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.0",
    "com.google.api.grpc:proto-google-common-protos:2.7.0",
    "com.kjetland:mbknor-jackson-jsonschema_2.13:1.0.39",
    "com.squareup.wire:wire-runtime:3.7.1",
    "com.squareup.wire:wire-schema:3.7.1",
    "io.swagger:swagger-annotations:1.6.3",
    "jakarta.servlet:jakarta.servlet-api:4.0.4",
    "jakarta.validation:jakarta.validation-api:2.0.2",
    "jakarta.ws.rs:jakarta.ws.rs-api:2.1.6",
    "org.jetbrains.kotlin:kotlin-stdlib:1.5.32",
    "org.json:json:20211205",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "6367bea36fa8e57981146a68970fa289b497f793f936da0e6b3aff452da70261",
        strip_prefix = "common-7.0.1",
        urls = ["https://github.com/confluentinc/common/archive/refs/tags/v7.0.1.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "a1ffce86b2dfb267bae5307ae8114f4af164e72848b864c4905eacab079b6ff8",
        strip_prefix = "schema-registry-7.0.1",
        urls = ["https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.0.1.tar.gz"],
    )
