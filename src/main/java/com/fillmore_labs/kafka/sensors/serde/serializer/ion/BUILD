load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "ion",
    srcs = glob(["*.java"]),
    visibility = ["//src/main/java/com/fillmore_labs/kafka/sensors/serde/ion/serialization:__pkg__"],
    deps = [
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_amazon_ion_ion_java",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
