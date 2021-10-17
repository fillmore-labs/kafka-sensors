load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "common",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//toolchain:checker",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:javax_inject_javax_inject",
    ],
)
