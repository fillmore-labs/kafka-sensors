load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "interop",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/avro",
        "//toolchain:checker",
        "@confluent_schema_registry//:kafka_schema_serializer",
        "@confluent_schema_registry//:kafka_streams_avro_serde",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:javax_inject_javax_inject",
        "@maven//:org_apache_avro_avro",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
