load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "serialization",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/specific:__pkg__",
        "//src/test:__subpackages__",
    ],
    deps = [
        "//src/main/avro/com/fillmore_labs/kafka/sensors/avro",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/confluent",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@confluent_schema_registry//:kafka_schema_serializer",
        "@confluent_schema_registry//:kafka_streams_avro_serde",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
