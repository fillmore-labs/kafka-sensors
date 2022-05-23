load("@contrib_rules_jvm//java:defs.bzl", "java_test_suite")
load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "test_schema",
    testonly = True,
    srcs = ["StateDurationTestSchema.java"],
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/generic/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/logicaltypes",
        "//toolchain:checker",
        "@maven//:org_apache_avro_avro",
    ],
)

java_test_suite(
    name = "tests",
    srcs = glob(
        ["*.java"],
        exclude = ["StateDurationTestSchema.java"],
    ),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        ":test_schema",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/generic/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/schema_store",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/avro",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_avro_avro",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
