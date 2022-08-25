load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
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
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
