load("@rules_java//java:defs.bzl", "java_library", "java_test")

java_library(
    name = "tests",
    testonly = True,
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/avro/com/fillmore_labs/kafka/sensors/avro",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/generic/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/reflect/serialization",
        "//toolchain:checker",
        "//toolchain:test",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_avro_avro",
    ],
)

java_test(
    name = "SchemaTest",
    size = "small",
    runtime_deps = [
        ":tests",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
)
