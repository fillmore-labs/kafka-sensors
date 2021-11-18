load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "confluent",
    srcs = glob(["*.java"]),
    visibility = ["//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent:__subpackages__"],
    deps = [
        "//toolchain:checker",
        "@confluent_schema_registry//:kafka_avro_serializer",
        "@confluent_schema_registry//:kafka_streams_avro_serde",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_avro_avro",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
