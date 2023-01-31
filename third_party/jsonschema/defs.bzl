""" Jackson jsonSchema Generator dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

JSONSCHEMA_ARTIFACTS = [
    "jakarta.validation:jakarta.validation-api:3.0.2",
]

def jsonschema_repositories():
    http_archive(
        name = "jsonschema",
        build_file = "//third_party/jsonschema:BUILD.jsonschema.bazel",
        sha256 = "1e5d6e73ccad63420ef34c0589af950fefa5c0367285366a029da27b4d6d8a82",
        strip_prefix = "mbknor-jackson-jsonSchema-1.0.39",
        url = "https://github.com/mbknor/mbknor-jackson-jsonSchema/archive/refs/tags/v1.0.39.tar.gz",
        patches = ["//third_party/jsonschema:jsonschema.patch"],
    )
