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
        sha256 = "68c4c527318478dce5f7788fb8694288250ae28c32ac50bfb6d90d35d2f2b5a1",
        strip_prefix = "common-7.3.1-rc221207224018",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.1-rc221207224018.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "a49fe8894770043f471a212fd081de1c7768605b6e215a86293d7622817a4747",
        strip_prefix = "schema-registry-7.3.1-rc221207224018",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.1-rc221207224018.tar.gz",
    )
