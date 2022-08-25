load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "mapper",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=MapperImpl$$"],
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/proto:__pkg__",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/mapping",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//src/main/proto/fillmore_labs/kafka/sensors/proto/v1",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@com_google_protobuf//:protobuf_java",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_inject_jakarta_inject_api",
    ],
)
