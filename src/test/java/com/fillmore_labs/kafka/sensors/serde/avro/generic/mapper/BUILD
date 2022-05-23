load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")

java_test_suite(
    name = "tests",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/generic/mapper",
        "//toolchain:checker",
        "//toolchain:test",
        "@maven//:com_google_testparameterinjector_test_parameter_injector",
        "@maven//:org_apache_avro_avro",
    ],
)
