load("@rules_java//java:defs.bzl", "java_library", "java_test")

java_library(
    name = "tests",
    testonly = True,
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//toolchain:checker",
        "//toolchain:test",
    ],
)

java_test(
    name = "SensorStateTest",
    size = "small",
    runtime_deps = [":tests"],
)
