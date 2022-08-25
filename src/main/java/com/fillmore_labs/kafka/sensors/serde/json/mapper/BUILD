load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "mapper",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=MapperImpl$$"],
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json:__pkg__",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json_iso:__pkg__",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/mapping",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_fasterxml_jackson_core_jackson_databind",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_inject_jakarta_inject_api",
    ],
)
