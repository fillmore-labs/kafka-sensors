""" Confluent dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

CONFLUENT_ARTIFACTS = [
    "com.google.api.grpc:proto-google-common-protos:2.6.0",
    "com.kjetland:mbknor-jackson-jsonschema_2.13:1.0.39",
    "com.squareup.wire:wire-schema:3.7.0",
    "io.swagger.core.v3:swagger-annotations:2.1.11",
    "org.jetbrains.kotlin:kotlin-stdlib:1.5.31",
    "org.json:json:20210307",
    "com.squareup.wire:wire-runtime:3.7.0",
    "com.github.erosb:everit-json-schema:1.14.0",
]

def confluent_repositories():
    http_archive(
        name = "confluent_common",
        build_file = "//third_party/confluent:BUILD.common.bazel",
        sha256 = "0dd0fd7acd918c29627cfdd29b9e41b525f3d6fea656acda962dd67a6863e4b6",
        strip_prefix = "common-ec808eca16e84d7c9c3d8804315d3ef8de7d4d67",
        urls = ["https://github.com/confluentinc/common/archive/ec808eca16e84d7c9c3d8804315d3ef8de7d4d67.tar.gz"],
    )
    http_archive(
        name = "confluent_schema_registry",
        build_file = "//third_party/confluent:BUILD.schema_registry.bazel",
        sha256 = "93b88a73fcd6787a59ad6e57cd13ac8721f79b88b6f151c518b7761256050727",
        strip_prefix = "schema-registry-981ed37407647ecb20ff8808b4cd0ec032af0123",
        urls = ["https://github.com/confluentinc/schema-registry/archive/981ed37407647ecb20ff8808b4cd0ec032af0123.tar.gz"],
    )
