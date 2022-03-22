load("@google_bazel_common//testing:test_defs.bzl", "gen_java_tests")

gen_java_tests(
    name = "tests",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/xml/serialization",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
