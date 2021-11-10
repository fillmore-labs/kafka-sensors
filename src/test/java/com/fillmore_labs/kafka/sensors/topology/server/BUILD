load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "server",
    testonly = True,
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//toolchain:checker",
        "@io_bazel_rules_scala_scala_library",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_flogger_flogger",
        "@maven//:org_apache_kafka_kafka_2_13",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
