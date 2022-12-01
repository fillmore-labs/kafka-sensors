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
        sha256 = "27e65e10780b2d297ae2a266788681f56130aa2a10ce8e225cfa1e8c31b92c08",
        strip_prefix = "common-7.3.1-rc221201041053",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.1-rc221201041053.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "7d0e71fd3098295159af4a43f4ac3939da9bdbfdbe640589de185d80d58debcb",
        strip_prefix = "schema-registry-7.3.1-rc221201041053",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.1-rc221201041053.tar.gz",
    )
