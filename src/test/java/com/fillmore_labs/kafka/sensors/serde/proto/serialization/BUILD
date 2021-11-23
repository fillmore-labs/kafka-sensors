load("@google_bazel_common//testing:test_defs.bzl", "gen_java_tests")

gen_java_tests(
    name = "tests",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/proto/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/proto",
        "//src/main/proto/fillmore_labs/kafka/sensors/proto/v1",
        "//src/test/proto/fillmore_labs/kafka/sensors/proto/v1",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@com_google_protobuf//:protobuf_java",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
