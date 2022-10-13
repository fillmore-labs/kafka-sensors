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
        sha256 = "7cc111979f76657554fcbacd0174156256cfaf273162f66ea600244b7e23ead2",
        strip_prefix = "common-7.3.0-rc221012225635",
        url = "https://github.com/confluentinc/common/archive/refs/tags/v7.3.0-rc221012225635.tar.gz",
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "66cb8d182a50cd5ccf92449828567c711a6934b2f18e54aef926ee3b0ce50a67",
        strip_prefix = "schema-registry-7.3.0-rc221008040822",
        url = "https://github.com/confluentinc/schema-registry/archive/refs/tags/v7.3.0-rc221008040822.tar.gz",
    )
