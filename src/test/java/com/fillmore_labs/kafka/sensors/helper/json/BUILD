load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "json",
    testonly = True,
    srcs = glob(["*.java"]),
    data = [
        "//testdata:input_samples",
        "//testdata:output_schema",
    ],
    visibility = ["//visibility:public"],
    deps = [
        "//toolchain:checker",
        "@maven//:com_fasterxml_jackson_core_jackson_databind",
        "@maven//:com_google_guava_guava",
        "@maven//:com_networknt_json_schema_validator",
    ],
)
