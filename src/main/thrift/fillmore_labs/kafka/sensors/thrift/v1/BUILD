load("@io_bazel_rules_scala//thrift:thrift.bzl", "thrift_library")
load("@io_bazel_rules_scala//twitter_scrooge:twitter_scrooge.bzl", "scrooge_java_library")
load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "v1",
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/thrift:__subpackages__",
        "//src/test:__subpackages__",
    ],
    exports = [
        ":sensor_state_java",
        ":state_duration_java",
    ],
)

scrooge_java_library(
    name = "sensor_state_java",
    deps = [":sensor_state_thrift"],
)

scrooge_java_library(
    name = "state_duration_java",
    deps = [":state_duration_thrift"],
)

thrift_library(
    name = "sensor_state_thrift",
    srcs = ["sensor_state.thrift"],
    absolute_prefix = "src/main/thrift",
)

thrift_library(
    name = "state_duration_thrift",
    srcs = ["state_duration.thrift"],
    absolute_prefix = "src/main/thrift",
    deps = [":sensor_state_thrift"],
)
