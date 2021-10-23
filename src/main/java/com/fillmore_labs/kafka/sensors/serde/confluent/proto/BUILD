load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "proto",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent:__pkg__",
        "//src/test:__subpackages__",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/proto/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@com_google_protobuf//:protobuf_java",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
