load("@google_bazel_common//testing:test_defs.bzl", "gen_java_tests")

gen_java_tests(
    name = "tests",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/interop",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/interop/context",
        "//toolchain:checker",
        "//toolchain:test",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_kafka_kafka_clients",
        "@maven//:org_checkerframework_checker_util",
    ],
)
