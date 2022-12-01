load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
    name = "tests",
    size = "small",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/interop",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/interop/context",
        "//toolchain:checker",
        "//toolchain:mockito",
        "//toolchain:test",
        "@confluent_schema_registry//:kafka_schema_registry_client",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_avro_avro",
        "@maven//:org_apache_kafka_kafka_clients",
        "@maven//:org_checkerframework_checker_util",
    ],
)
