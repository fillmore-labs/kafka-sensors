load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "confluent",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/generic",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/json",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/proto",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/reflect",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/specific",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_inject_jakarta_inject_api",
    ],
)
