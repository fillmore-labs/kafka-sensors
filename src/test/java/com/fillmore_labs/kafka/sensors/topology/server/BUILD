load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "server",
    testonly = True,
    srcs = glob(["*.java"]),
    visibility = ["//src/test:__subpackages__"],
    deps = [
        "//toolchain:checker",
        "//toolchain:test",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_flogger_flogger",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_kafka_kafka_2_13",
        "@maven//:org_apache_kafka_kafka_clients",
        "@maven//:org_scala_lang_scala_library",
    ],
)
