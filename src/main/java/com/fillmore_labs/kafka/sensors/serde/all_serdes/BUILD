load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "all_serdes",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/gson",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/ion",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json_iso",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/mixin",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/proto",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/thrift",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/xml",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
    ],
)
