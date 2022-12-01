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
        sha256 = "ac2ecbe330058f60e80b7b1759a0023a4fdf274defb023f54ee9282bef5ba1c1",
        strip_prefix = "common-7.3.1-15",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.1-15.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "66ab337ee2989ca8baf3ae3a29db48c5233a308297a0cdab670a6f7bd157eee0",
        strip_prefix = "schema-registry-7.3.1-20",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.1-20.tar.gz",
    )
