load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "xml",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/xml/mapper",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/xml/serialization",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
