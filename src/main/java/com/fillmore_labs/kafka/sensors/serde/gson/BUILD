load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "gson",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/gson/mapper",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/gson/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
