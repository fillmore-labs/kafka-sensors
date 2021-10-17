load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "context",
    srcs = glob(["*.java"]),
    javacopts = [
        "-Xlint:-cast",
        "-AskipDefs=\\.Dagger",
    ],
    visibility = ["//src/main/java/com/fillmore_labs/kafka/sensors/benchmark:__pkg__"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/all_serdes",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
    ],
)
