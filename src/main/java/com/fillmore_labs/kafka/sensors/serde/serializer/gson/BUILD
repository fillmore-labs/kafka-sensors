load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "gson",
    srcs = glob(["*.java"]),
    visibility = ["//src/main/java/com/fillmore_labs/kafka/sensors/serde/gson/serialization:__pkg__"],
    deps = [
        "//toolchain:checker",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
