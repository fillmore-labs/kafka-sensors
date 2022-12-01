load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
    name = "tests",
    size = "small",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/xml",
        "//toolchain:checker",
        "//toolchain:mockito",
        "//toolchain:test",
        "@maven//:jakarta_xml_bind_jakarta_xml_bind_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
