load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "context",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    visibility = ["//src/main/java/com/fillmore_labs/kafka/sensors/app:__pkg__"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/configuration",
        "//src/main/java/com/fillmore_labs/kafka/sensors/lifecycle",
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/proto",
        "//src/main/java/com/fillmore_labs/kafka/sensors/streams",
        "//src/main/java/com/fillmore_labs/kafka/sensors/topology",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:io_helidon_config_helidon_config",
        "@maven//:org_apache_kafka_kafka_clients",
        "@maven//:org_apache_kafka_kafka_streams",
    ],
)
