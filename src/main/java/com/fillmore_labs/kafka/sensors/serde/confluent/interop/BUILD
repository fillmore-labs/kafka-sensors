load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "interop",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//toolchain:checker",
        "@confluent_schema_registry//:kafka_schema_registry_client",
        "@confluent_schema_registry//:kafka_schema_serializer",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_avro_avro",
    ],
)
