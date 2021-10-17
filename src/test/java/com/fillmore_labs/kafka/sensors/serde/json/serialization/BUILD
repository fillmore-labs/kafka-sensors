load("@rules_java//java:defs.bzl", "java_library", "java_test")

java_library(
    name = "tests",
    testonly = True,
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json/serialization",
        "//src/test/java/com/fillmore_labs/kafka/sensors/helper/json",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@maven//:com_google_guava_guava",
        "@maven//:com_networknt_json_schema_validator",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)

java_test(
    name = "CanReadTest",
    size = "small",
    runtime_deps = [":tests"],
)

java_test(
    name = "SerializationTest",
    size = "small",
    runtime_deps = [":tests"],
)
