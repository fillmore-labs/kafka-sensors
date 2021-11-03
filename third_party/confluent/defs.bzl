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
        sha256 = "2171f6fc92234c040496a3964ae45e7764fb0200c3dd3c926f69776f14488477",
        strip_prefix = "common-53d73b140eb55cc91fd3fbd9db7f24944b5431df",
        urls = ["https://github.com/confluentinc/common/archive/53d73b140eb55cc91fd3fbd9db7f24944b5431df.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "8daf26de1c0d999a2c9731b647e959717dc0e4415bdce0c3272016192387eec4",
        strip_prefix = "schema-registry-861fcfd68f7fb583b0067feb100eec315da250b0",
        urls = ["https://github.com/confluentinc/schema-registry/archive/861fcfd68f7fb583b0067feb100eec315da250b0.tar.gz"],
    )
