""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.github.erosb:everit-json-schema:1.14.1",
    "com.google.api.grpc:proto-google-common-protos:2.14.0",
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
        sha256 = "abca41edb26373a50fe022069fb8433f1deed59442b50766c34d01626d2373fe",
        strip_prefix = "common-7.4.0-rc230203013019",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.4.0-rc230203013019.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "fccf9b2d0180ce23a80f7b72633e9a46159c2cddb86f8df476eb90571d161073",
        strip_prefix = "schema-registry-7.4.0-rc230203013019",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.4.0-rc230203013019.tar.gz",
    )
