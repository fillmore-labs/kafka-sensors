load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "mapper",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=MapperImpl$$"],
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/thrift:__pkg__",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/mapping",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//src/main/thrift/fillmore_labs/kafka/sensors/thrift/v1",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
    ],
)
