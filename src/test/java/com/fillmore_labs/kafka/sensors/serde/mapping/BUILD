load("@google_bazel_common//testing:test_defs.bzl", "gen_java_tests")

gen_java_tests(
    name = "tests",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/mapping",
        "//toolchain:checker",
        "//toolchain:test",
        "@maven//:com_google_guava_guava",
    ],
)
