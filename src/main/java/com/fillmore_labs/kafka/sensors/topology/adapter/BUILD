load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "adapter",
    srcs = glob(["*.java"]),
    visibility = ["//src/main/java/com/fillmore_labs/kafka/sensors/topology:__pkg__"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/logic",
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_kafka_kafka_streams",
    ],
)
