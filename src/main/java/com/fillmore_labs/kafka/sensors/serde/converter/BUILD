load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "converter",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde:__subpackages__",
        "//src/test:__subpackages__",
    ],
    deps = [
        "//toolchain:checker",
        "@maven//:com_google_errorprone_error_prone_annotations",
    ],
)
