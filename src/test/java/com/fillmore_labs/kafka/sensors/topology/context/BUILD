load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "context",
    testonly = True,
    srcs = glob(["*.java"]),
    javacopts = [
        "-Xlint:-cast",
        "-AskipDefs=\\.Dagger",
    ],
    visibility = ["//src/test:__subpackages__"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/configuration",
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/all_serdes",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/topology",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:org_apache_kafka_kafka_clients",
        "@maven//:org_apache_kafka_kafka_streams",
        "@maven//:org_apache_kafka_kafka_streams_test_utils",
    ],
)
