load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "context",
    testonly = True,
    srcs = glob(["*.java"]),
    javacopts = [
        "-Xlint:-cast",
        "-AskipDefs=\\.Dagger",
    ],
    visibility = ["//src/test/java/com/fillmore_labs/kafka/sensors/topology:__pkg__"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/all_serdes",
        "//src/main/java/com/fillmore_labs/kafka/sensors/topology",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/test/java/com/fillmore_labs/kafka/sensors/topology/server",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:junit_junit",
        "@maven//:org_apache_kafka_kafka_clients",
        "@maven//:org_apache_kafka_kafka_streams",
        "@maven//:org_apache_kafka_kafka_streams_test_utils",
    ],
)
