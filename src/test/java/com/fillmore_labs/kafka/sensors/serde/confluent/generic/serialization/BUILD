load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
    name = "tests",
    size = "small",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/generic/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/generic/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/confluent",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/avro/generic/serialization:test_schema",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@confluent_schema_registry//:kafka_streams_avro_serde",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_avro_avro",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
