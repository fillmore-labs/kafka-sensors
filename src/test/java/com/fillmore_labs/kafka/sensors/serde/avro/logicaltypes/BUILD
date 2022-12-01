load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
    name = "tests",
    size = "small",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/logicaltypes",
        "//toolchain:checker",
        "//toolchain:test",
        "@maven//:org_apache_avro_avro",
    ],
)
