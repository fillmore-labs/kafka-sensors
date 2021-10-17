load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "serialization",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/proto:__subpackages__",
        "//src/test:__subpackages__",
    ],
    exports = [
        "//src/main/proto/fillmore_labs/kafka/sensors/v1:sensorsv1_java_proto",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/proto",
        "//src/main/proto/fillmore_labs/kafka/sensors/v1:sensorsv1_java_proto",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:immutables",
        "@com_google_protobuf//:protobuf_java",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
