load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
    name = "tests",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/ion/serialization",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
