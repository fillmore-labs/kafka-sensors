load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "thrift",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/thrift/mapper",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/thrift/serialization",
        "//src/main/thrift/fillmore_labs/kafka/sensors/thrift/v1",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
