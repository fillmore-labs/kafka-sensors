load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "context",
    testonly = True,
    srcs = glob(["*.java"]),
    javacopts = [
        "-Xlint:-cast",
        "-AskipDefs=\\.Dagger",
    ],
    visibility = ["//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/interop:__pkg__"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/schema_store",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/interop",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
