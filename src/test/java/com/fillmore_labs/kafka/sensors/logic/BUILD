load("@rules_java//java:defs.bzl", "java_library", "java_test")

java_library(
    name = "tests",
    testonly = True,
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/logic",
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//toolchain:checker",
        "//toolchain:test",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
    ],
)

java_test(
    name = "CalculatorTest",
    size = "small",
    runtime_deps = [":tests"],
)
