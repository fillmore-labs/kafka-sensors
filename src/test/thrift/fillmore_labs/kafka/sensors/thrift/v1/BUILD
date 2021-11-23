load("@io_bazel_rules_scala//thrift:thrift.bzl", "thrift_library")
load("@io_bazel_rules_scala//twitter_scrooge:twitter_scrooge.bzl", "scrooge_java_library")
load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "v1",
    testonly = True,
    visibility = ["//src/test:__subpackages__"],
    exports = [
        ":state_duration_java",
    ],
)

scrooge_java_library(
    name = "state_duration_java",
    deps = [":state_duration_thrift"],
)

thrift_library(
    name = "state_duration_thrift",
    srcs = ["state_duration.thrift"],
    absolute_prefix = "src/test/thrift",
    deps = ["//src/main/thrift/fillmore_labs/kafka/sensors/thrift/v1:sensor_state_thrift"],
)
