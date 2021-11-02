""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.0",
    "com.google.api.grpc:proto-google-common-protos:2.6.0",
    "com.kjetland:mbknor-jackson-jsonschema_2.13:1.0.39",
    "com.squareup.wire:wire-runtime:3.7.0",
    "com.squareup.wire:wire-schema:3.7.0",
    "io.swagger.core.v3:swagger-annotations:2.1.11",
    "jakarta.servlet:jakarta.servlet-api:4.0.4",
    "jakarta.validation:jakarta.validation-api:2.0.2",
    "jakarta.ws.rs:jakarta.ws.rs-api:2.1.6",
    "org.jetbrains.kotlin:kotlin-stdlib:1.5.31",
    "org.json:json:20210307",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "4ab39def361c0af972c51d3d914df41415b3fdfb10aaaa70d3282b56ec5240a4",
        strip_prefix = "common-66cf390b821e381f55def51443244e2b60345698",
        urls = ["https://github.com/confluentinc/common/archive/66cf390b821e381f55def51443244e2b60345698.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "41d1a3ae9213ee352a47bcc78a0824e1328ce1a64e3fc7121651387e7d9ffab0",
        strip_prefix = "schema-registry-27405ea2dd27fb7fe3e87f46443e441501631b35",
        urls = ["https://github.com/confluentinc/schema-registry/archive/27405ea2dd27fb7fe3e87f46443e441501631b35.tar.gz"],
    )
