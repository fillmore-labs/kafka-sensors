""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.9.2",
    "com.kjetland:mbknor-jackson-jsonschema_2.13:1.0.39",
    "com.squareup.wire:wire-runtime-jvm:4.4.1",
    "com.squareup.wire:wire-schema-jvm:4.4.1",
    "io.swagger.core.v3:swagger-annotations-jakarta:2.2.2",
    "jakarta.validation:jakarta.validation-api:2.0.2",  # Legacy, update with jsonschema
    "org.jetbrains.kotlin:kotlin-stdlib:1.7.10",
    "org.json:json:20220320",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "3b796549293548cd4d235aa532b6012b3374e481ff25a23c2743a276ccdbd1e1",
        strip_prefix = "common-7.2.1",
        urls = ["https://github.com/confluentinc/common/archive/refs/tags/v7.2.1.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "9ce1a2b7c5721d97a7451e6ca879284b61bbcd1b91e39b50987a2ae4663d9182",
        strip_prefix = "schema-registry-7.2.1",
        urls = ["https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.2.1.tar.gz"],
    )
