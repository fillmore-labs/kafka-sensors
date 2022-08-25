load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
    name = "tests",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/mixin/serialization",
        "//src/test/java/com/fillmore_labs/kafka/sensors/helper/json",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@maven//:com_fasterxml_jackson_core_jackson_databind",
        "@maven//:com_networknt_json_schema_validator",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
