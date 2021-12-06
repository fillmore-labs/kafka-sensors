load("@google_bazel_common//testing:test_defs.bzl", "gen_java_tests")

gen_java_tests(
    name = "tests",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/app:app_library",
        "//src/main/java/com/fillmore_labs/kafka/sensors/benchmark:benchmark_library",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/all_serdes",
        "//toolchain:checker",
        "//toolchain:test",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:io_github_classgraph_classgraph",
        "@maven//:io_github_toolfactory_narcissus",
    ],
)
