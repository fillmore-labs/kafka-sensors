load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "common",
    testonly = True,
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//toolchain:checker",
        "//toolchain:dagger",
    ],
)
