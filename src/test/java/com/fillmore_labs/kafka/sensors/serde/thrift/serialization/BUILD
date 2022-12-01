load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
    name = "tests",
    size = "small",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/thrift",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/thrift/serialization",
        "//src/main/thrift/fillmore_labs/kafka/sensors/thrift/v1",
        "//src/test/thrift/fillmore_labs/kafka/sensors/thrift/v1",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
